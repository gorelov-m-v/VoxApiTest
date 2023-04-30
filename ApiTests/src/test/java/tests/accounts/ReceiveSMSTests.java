package tests.accounts;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import requests.accounts.AddAccountRequest;
import requests.accounts.AttachPhoneNumberRequest;
import requests.accounts.ControlSmsRequest;
import requests.accounts.SetAccountDocumentRequest;
import requests.model.*;
import response.accounts.AddAccountResponse;
import response.accounts.AttachPhoneNumberResponse;
import response.accounts.SetAccountDocumentResponse;
import response.accounts.UniversalResponse;

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
    public void smoke() throws IOException, TimeoutException {

        User requestedUser = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedUser);

        AccountDocument accountDocument = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocument);

        AttachPhoneNumber attachPhoneNumber = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumber);

        ControlSms controlSms = app.generate().randomControlSms(addAccountResponse,attachPhoneNumberResponse);
        universalResponse = controlSmsRequest.ControlSms(controlSms);

        ReceivedSmsDataSet receivedSmsDataSet = app.generate().randomReceivedSmsDataSet(attachPhoneNumberResponse);
        String message = app.generate().randomReceivedSmsDataSet(attachPhoneNumberResponse, receivedSmsDataSet);
        System.out.println(message);

        app.p2p().publish(app.getProperty("rabbitmq.exchange.sms"),
                          app.getProperty("rabbitmq.routing-key.receiveSms"),
                          message);

        System.out.println(app.db().getSmsByUUID(receivedSmsDataSet.getUuid()).getId());
    }
}