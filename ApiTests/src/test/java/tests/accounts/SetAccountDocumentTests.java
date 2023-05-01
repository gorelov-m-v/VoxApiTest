package tests.accounts;

import org.testng.annotations.Test;
import http.model.accounts.add.AddAccountRequest;
import http.model.accounts.setdocument.SetAccountDocumentRequest;
import http.model.accounts.setdocument.AccountDocumentDataSet;
import http.model.accounts.add.AddAccountDataSet;
import http.model.accounts.add.AddAccountResponse;
import http.model.accounts.setdocument.SetAccountDocumentResponse;

public class SetAccountDocumentTests extends TestBase {

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    SetAccountDocumentRequest setDocumentRequest = new SetAccountDocumentRequest();
    SetAccountDocumentResponse setDocumentResponse;

    @Test
    public void smoke() {
        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        AccountDocumentDataSet accountDocumentDataSet = app.generate().randomAccountDocument(addAccountResponse);
        setDocumentResponse = setDocumentRequest.setAccountDocument(accountDocumentDataSet);
    }
}
