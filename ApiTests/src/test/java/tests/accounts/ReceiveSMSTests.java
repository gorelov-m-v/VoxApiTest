package tests.accounts;

import http.model.sms.received.ReceivedSMSRequest;
import http.model.sms.received.ReceiverSmsHTTPDataSet;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import http.model.accounts.add.AddAccountRequest;
import http.model.accounts.add.AddAccountDataSet;
import http.model.accounts.setdocument.AccountDocumentDataSet;
import http.model.phonenumbers.attach.AttachPhoneNumberDataSet;
import http.model.phonenumbers.attach.AttachPhoneNumberRequest;
import http.model.sms.control.ControlSmsDataSet;
import http.model.sms.control.ControlSmsRequest;
import http.model.accounts.setdocument.SetAccountDocumentRequest;
import http.model.accounts.add.AddAccountResponse;
import http.model.phonenumbers.attach.AttachPhoneNumberResponse;
import http.model.accounts.setdocument.SetAccountDocumentResponse;
import http.model.sms.received.ReceivedSmsMQDataSet;
import http.model.universal.UniversalResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertNotNull;

public class ReceiveSMSTests extends TestBase{

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;
    AttachPhoneNumberRequest attachPhoneNumberRequest = new AttachPhoneNumberRequest();
    AttachPhoneNumberResponse attachPhoneNumberResponse;
    ControlSmsRequest controlSmsRequest = new ControlSmsRequest();
    UniversalResponse universalResponse;
    ReceivedSMSRequest receivedSMSRequest = new ReceivedSMSRequest();

    @Test
    public void smokeByQueue() throws IOException, TimeoutException, InterruptedException {

        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);

        AttachPhoneNumberDataSet attachPhoneNumberDataSet = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumberDataSet);

        ControlSmsDataSet controlSmsDataSet = app.generate().randomControlSmsDataSet(addAccountResponse,attachPhoneNumberResponse);
        universalResponse = controlSmsRequest.controlSms(controlSmsDataSet);

        ReceivedSmsMQDataSet receivedSmsMQDataSet = app.generate().randomReceivedSmsMQDataSet(attachPhoneNumberResponse);
        String message = app.generate().randomReceivedSmsMQDataSet(receivedSmsMQDataSet);
        System.out.println(message);

        app.mqp().publish(app.getProperty("rabbitmq.exchange.sms"),
                          app.getProperty("rabbitmq.routing-key.receiveSms"),
                          message);

        assertNotNull(app.db().getSmsByUUID(receivedSmsMQDataSet).getId());
    }

    @Test(enabled = false)
    public void smokeByHTTP() throws InterruptedException {

        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);

        AttachPhoneNumberDataSet attachPhoneNumberDataSet = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumberDataSet);

        ControlSmsDataSet controlSmsDataSet = app.generate()
                .randomControlSmsDataSet(addAccountResponse,attachPhoneNumberResponse);
        universalResponse = controlSmsRequest.controlSms(controlSmsDataSet);

        ReceiverSmsHTTPDataSet receiverSmsHTTPDataSet = app.generate()
                .randomReceivedSmsHTTPDataSet(attachPhoneNumberResponse);

        int before = app.db().getAllSmsId().size();
        receivedSMSRequest.receivedSms(receiverSmsHTTPDataSet);
        int after = app.db().getAllSmsId().size();

        assertThat(after).isEqualTo(before + 1);
    }
}