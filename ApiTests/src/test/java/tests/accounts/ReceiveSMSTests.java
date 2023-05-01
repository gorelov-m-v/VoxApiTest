package tests.accounts;

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
import http.model.sms.received.ReceivedSmsDataSet;
import http.model.universal.UniversalResponse;

public class ReceiveSMSTests extends TestBase{

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;
    AttachPhoneNumberRequest attachPhoneNumberRequest = new AttachPhoneNumberRequest();
    AttachPhoneNumberResponse attachPhoneNumberResponse;

    ControlSmsRequest controlSmsRequest = new ControlSmsRequest();
    UniversalResponse universalResponse;

    @Test
    public void smokeByQueue() throws IOException, TimeoutException {

        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);

        AttachPhoneNumberDataSet attachPhoneNumberDataSet = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumberDataSet);

        ControlSmsDataSet controlSmsDataSet = app.generate().randomControlSms(addAccountResponse,attachPhoneNumberResponse);
        universalResponse = controlSmsRequest.ControlSms(controlSmsDataSet);

        ReceivedSmsDataSet receivedSmsDataSet = app.generate().randomReceivedSmsDataSet(attachPhoneNumberResponse);
        String message = app.generate().randomReceivedSmsDataSet(attachPhoneNumberResponse, receivedSmsDataSet);
        System.out.println(message);

        app.p2p().publish(app.getProperty("rabbitmq.exchange.sms"),
                          app.getProperty("rabbitmq.routing-key.receiveSms"),
                          message);

        System.out.println(app.db().getSmsByUUID(receivedSmsDataSet.getUuid()).getId());
    }
}