package tests.accounts;

import org.testng.annotations.Test;
import model.http.accounts.add.AddAccountRequest;
import model.http.phonenumbers.attach.AttachPhoneNumberRequest;
import model.http.accounts.setdocument.SetAccountDocumentRequest;
import model.http.accounts.setdocument.AccountDocumentDataSet;
import model.http.phonenumbers.attach.AttachPhoneNumberDataSet;
import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.add.AddAccountResponse;
import model.http.phonenumbers.attach.AttachPhoneNumberResponse;
import model.http.accounts.setdocument.SetAccountDocumentResponse;

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
