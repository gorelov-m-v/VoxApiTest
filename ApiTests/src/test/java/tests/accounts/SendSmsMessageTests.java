package tests.accounts;

import http.model.accounts.add.AddAccountDataSet;
import http.model.accounts.add.AddAccountRequest;
import http.model.accounts.add.AddAccountResponse;
import http.model.accounts.editinfo.EditAccountInfoDataSet;
import http.model.accounts.editinfo.EditAccountInfoRequest;
import http.model.accounts.editinfo.dataset.*;
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
    EditAccountInfoRequest editAccountInfoRequest = new EditAccountInfoRequest();
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

    @Test
    public void sendSmsMessageByCreditUser() throws InterruptedException {
        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);

        EditAccountInfoDataSet editAccountInfoDataSet = new EditAccountInfoDataSet()
                .withFinancialInfo(new FinancialInfo()
                        .withIsCreditUser(new IsCreditUser()
                                .withValue(true))
                        .withAccountId(String.valueOf((addAccountResponse.account_id()))))
                .withLimitsInfo(new LimitsInfo()
                        .withAccountId(String.valueOf((addAccountResponse.account_id())))
                        .withFinancialLimits(new FinancialLimits()
                                .withCreditLimit(new CreditLimit().withValue("200"))));
        editAccountInfoRequest.editAccountInfo(addAccountResponse, editAccountInfoDataSet);

        AttachPhoneNumberDataSet attachPhoneNumberDataSet = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumberDataSet);

        ControlSmsDataSet controlSmsDataSet = app.generate().randomControlSmsDataSet(addAccountResponse,attachPhoneNumberResponse);
        universalResponse = controlSmsRequest.controlSms(controlSmsDataSet);

        SendSmsMessageDataSet sendSmsMessageDataSet
                = app.generate().randomSendSmsMessageDataSet(addAccountResponse, attachPhoneNumberResponse);


        sendSmsMessageResponse = sendSmsMessageRequest.sendSmsMessage(sendSmsMessageDataSet);
    }
}
