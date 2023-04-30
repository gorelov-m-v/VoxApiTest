package tests.accounts;

import org.testng.annotations.Test;
import requests.accounts.AddAccountRequest;
import requests.accounts.AttachPhoneNumberRequest;
import requests.accounts.SetAccountDocumentRequest;
import requests.model.AccountDocument;
import requests.model.AttachPhoneNumber;
import requests.model.User;
import response.accounts.AddAccountResponse;
import response.accounts.AttachPhoneNumberResponse;
import response.accounts.SetAccountDocumentResponse;

public class AttachPhoneNumberTests extends TestBase {

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;
    AttachPhoneNumberRequest attachPhoneNumberRequest = new AttachPhoneNumberRequest();
    AttachPhoneNumberResponse attachPhoneNumberResponse;

    @Test
    public void smoke() {
        User requestedUser = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedUser);

        AccountDocument accountDocument = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocument);

        AttachPhoneNumber attachPhoneNumber = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumber);
    }

}
