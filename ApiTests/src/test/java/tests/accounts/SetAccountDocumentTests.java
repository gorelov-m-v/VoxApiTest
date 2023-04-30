package tests.accounts;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requests.accounts.AddAccountRequest;
import requests.accounts.SetAccountDocumentRequest;
import requests.model.AccountDocument;
import requests.model.User;
import response.accounts.AddAccountResponse;
import response.accounts.SetAccountDocumentResponse;
import java.io.IOException;

public class SetAccountDocumentTests extends TestBase {

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;

    @Test
    public void smoke() {
        User requestedUser = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedUser);

        AccountDocument accountDocument = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocument);
    }
}
