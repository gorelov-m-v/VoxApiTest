package tests.accounts;

import org.testng.annotations.Test;
import model.http.accounts.add.AddAccountRequest;
import model.http.accounts.setdocument.SetAccountDocumentRequest;
import model.http.accounts.setdocument.AccountDocumentDataSet;
import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.add.AddAccountResponse;
import model.http.accounts.setdocument.SetAccountDocumentResponse;

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
