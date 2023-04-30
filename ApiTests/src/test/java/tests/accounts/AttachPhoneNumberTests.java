package tests.accounts;

import org.testng.annotations.Test;
import requests.accounts.AddAccountRequest;
import requests.accounts.AttachPhoneNumberRequest;
import requests.accounts.SetAccountDocumentRequest;
import requests.model.AccountDocumentDataSet;
import requests.model.AttachPhoneNumberDataSet;
import requests.model.UserDataSet;
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
        UserDataSet requestedUserDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedUserDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);

        AttachPhoneNumberDataSet attachPhoneNumberDataSet = app.generate().randomAttachPhoneNumber(addAccountResponse);
        attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(attachPhoneNumberDataSet);
    }

}
