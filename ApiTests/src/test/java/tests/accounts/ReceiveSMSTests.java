package tests.accounts;

import model.http.sms.received.ReceivedSMSRequest;
import model.http.sms.received.ReceiverSmsHTTPDataSet;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import model.http.accounts.add.AddAccountRequest;
import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.setdocument.AccountDocumentDataSet;
import model.http.phonenumbers.attach.AttachPhoneNumberDataSet;
import model.http.phonenumbers.attach.AttachPhoneNumberRequest;
import model.http.sms.control.ControlSmsDataSet;
import model.http.sms.control.ControlSmsRequest;
import model.http.accounts.setdocument.SetAccountDocumentRequest;
import model.http.accounts.add.AddAccountResponse;
import model.http.phonenumbers.attach.AttachPhoneNumberResponse;
import model.http.accounts.setdocument.SetAccountDocumentResponse;
import model.http.sms.received.ReceivedSmsMQDataSet;
import model.http.universal.UniversalResponse;
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