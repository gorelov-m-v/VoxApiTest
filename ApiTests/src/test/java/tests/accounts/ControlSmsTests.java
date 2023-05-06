package tests.accounts;

import org.testng.annotations.Test;
import model.http.accounts.add.AddAccountRequest;
import model.http.phonenumbers.attach.AttachPhoneNumberRequest;
import model.http.sms.control.ControlSmsRequest;
import model.http.accounts.setdocument.SetAccountDocumentRequest;
import model.http.accounts.setdocument.AccountDocumentDataSet;
import model.http.phonenumbers.attach.AttachPhoneNumberDataSet;
import model.http.sms.control.ControlSmsDataSet;
import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.add.AddAccountResponse;
import model.http.phonenumbers.attach.AttachPhoneNumberResponse;
import model.http.accounts.setdocument.SetAccountDocumentResponse;
import model.http.universal.UniversalResponse;

public class ControlSmsTests extends TestBase {

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;
    AttachPhoneNumberRequest attachPhoneNumberRequest = new AttachPhoneNumberRequest();
    AttachPhoneNumberResponse attachPhoneNumberResponse;
    ControlSmsRequest controlSmsRequest = new ControlSmsRequest();
    UniversalResponse universalResponse;

    @Test
    public void smokeContolSms() {
        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);

        AttachPhoneNumberDataSet attachPhoneNumberDataSet = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumberDataSet);

        ControlSmsDataSet controlSmsDataSet = app.generate().randomControlSmsDataSet(addAccountResponse,attachPhoneNumberResponse);
        universalResponse = controlSmsRequest.controlSms(controlSmsDataSet);
    }
}
