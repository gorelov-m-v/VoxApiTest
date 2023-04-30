package tests.accounts;

import org.testng.annotations.Test;
import requests.accounts.AddAccountRequest;
import requests.accounts.SetAccountDocumentRequest;
import requests.model.AccountDocumentDataSet;
import requests.model.UserDataSet;
import response.accounts.AddAccountResponse;
import response.accounts.SetAccountDocumentResponse;

public class SetAccountDocumentTests extends TestBase {

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;

    @Test
    public void smoke() {
        UserDataSet requestedUserDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedUserDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);
    }
}
