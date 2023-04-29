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
import java.time.LocalDate;

import static org.assertj.core.util.DateUtil.now;

public class SetAccountDocumentTests extends TestBase {

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;


    @BeforeMethod
    public void setUp() throws IOException {
        app.init();
        RestAssured.baseURI = app.getProperty("papi.host");
    }

    @Test
    public void smoke() {
        User requestedUser = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedUser);

        AccountDocument accountDocument = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocument);
    }
}
