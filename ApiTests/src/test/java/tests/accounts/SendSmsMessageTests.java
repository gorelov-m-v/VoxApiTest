package tests.accounts;

import http.model.accounts.add.AddAccountDataSet;
import http.model.accounts.add.AddAccountRequest;
import http.model.accounts.add.AddAccountResponse;
import http.model.accounts.setdocument.AccountDocumentDataSet;
import http.model.accounts.setdocument.SetAccountDocumentRequest;
import http.model.accounts.setdocument.SetAccountDocumentResponse;
import http.model.phonenumbers.attach.AttachPhoneNumberDataSet;
import http.model.phonenumbers.attach.AttachPhoneNumberRequest;
import http.model.phonenumbers.attach.AttachPhoneNumberResponse;
import http.model.sms.control.ControlSmsDataSet;
import http.model.sms.control.ControlSmsRequest;
import http.model.sms.send.SendSmsMessageDataSet;
import http.model.sms.send.SendSmsMessageRequest;
import http.model.sms.send.SendSmsMessageResponse;
import http.model.universal.UniversalResponse;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SendSmsMessageTests extends TestBase {
    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;
    AttachPhoneNumberRequest attachPhoneNumberRequest = new AttachPhoneNumberRequest();
    AttachPhoneNumberResponse attachPhoneNumberResponse;
    ControlSmsRequest controlSmsRequest = new ControlSmsRequest();
    UniversalResponse universalResponse;
    SendSmsMessageRequest sendSmsMessageRequest = new SendSmsMessageRequest();
    SendSmsMessageResponse sendSmsMessageResponse;

    @Test
    public void sendSmsMessageSmoke() throws InterruptedException {
        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);

        AttachPhoneNumberDataSet attachPhoneNumberDataSet = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumberDataSet);

        ControlSmsDataSet controlSmsDataSet = app.generate().randomControlSmsDataSet(addAccountResponse,attachPhoneNumberResponse);
        universalResponse = controlSmsRequest.controlSms(controlSmsDataSet);

        SendSmsMessageDataSet sendSmsMessageDataSet
                = app.generate().randomSendSmsMessageDataSet(addAccountResponse, attachPhoneNumberResponse);

        int before = app.db().getAllSmsHistory().size();
        sendSmsMessageResponse = sendSmsMessageRequest.sendSmsMessage(sendSmsMessageDataSet);
        int after = app.db().getAllSmsHistory().size();

        assertThat(after).isEqualTo(before + 1);
    }
}
