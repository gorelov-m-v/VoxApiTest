package tests.accounts;

import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.add.AddAccountRequest;
import model.http.accounts.add.AddAccountResponse;
import model.http.accounts.editinfo.EditAccountInfoDataSet;
import model.http.accounts.editinfo.EditAccountInfoRequest;
import model.http.accounts.editinfo.dataset.*;
import model.http.accounts.setdocument.AccountDocumentDataSet;
import model.http.accounts.setdocument.SetAccountDocumentRequest;
import model.http.accounts.setdocument.SetAccountDocumentResponse;
import model.http.phonenumbers.attach.AttachPhoneNumberDataSet;
import model.http.phonenumbers.attach.AttachPhoneNumberRequest;
import model.http.phonenumbers.attach.AttachPhoneNumberResponse;
import model.http.sms.control.ControlSmsDataSet;
import model.http.sms.control.ControlSmsRequest;
import model.http.sms.send.SendSmsMessageDataSet;
import model.http.sms.send.SendSmsMessageRequest;
import model.http.sms.send.SendSmsMessageResponse;
import model.http.universal.UniversalResponse;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
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

        int before = app.db().getAllSmsId().size();
        sendSmsMessageResponse = sendSmsMessageRequest.sendSmsMessage(sendSmsMessageDataSet);
        int after = app.db().getAllSmsId().size();

        assertThat(after).isEqualTo(before + 1);
    }

    @Test
    public void sendSmsMessageByCreditUser() throws InterruptedException, IOException, TimeoutException {
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

        String message = app.generate().randomSmsSendingInfoMQDataSet(app.db().getSms(sendSmsMessageResponse));

        app.mqp().publish(app.getProperty("rabbitmq.exchange.sms"),
                app.getProperty("rabbitmq.routing-key.smsSendingInfo"),
                message);

        assertThat(app.db().getSms(sendSmsMessageResponse).gettransaction_id()).isNotNull();
    }
}
