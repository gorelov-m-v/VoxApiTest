package tests.accounts;

import org.testng.annotations.Test;
import http.model.accounts.add.AddAccountRequest;
import http.model.phonenumbers.attach.AttachPhoneNumberRequest;
import http.model.accounts.setdocument.SetAccountDocumentRequest;
import http.model.accounts.setdocument.AccountDocumentDataSet;
import http.model.phonenumbers.attach.AttachPhoneNumberDataSet;
import http.model.accounts.add.AddAccountDataSet;
import http.model.accounts.add.AddAccountResponse;
import http.model.phonenumbers.attach.AttachPhoneNumberResponse;
import http.model.accounts.setdocument.SetAccountDocumentResponse;

public class AttachPhoneNumberTests extends TestBase {

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;
    AttachPhoneNumberRequest attachPhoneNumberRequest = new AttachPhoneNumberRequest();
    AttachPhoneNumberResponse attachPhoneNumberResponse;

    @Test
    public void smoke() {
        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);

        AttachPhoneNumberDataSet attachPhoneNumberDataSet = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumberDataSet);
    }

}
