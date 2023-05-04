package tests.accounts;

import http.model.accounts.add.AddAccountDataSet;
import http.model.accounts.add.AddAccountRequest;
import http.model.accounts.add.AddAccountResponse;
import http.model.accounts.editinfo.EditAccountInfoDataSet;
import http.model.accounts.editinfo.EditAccountInfoRequest;
import http.model.accounts.editinfo.dataset.*;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

public class EditAccountInfoTests extends TestBase {

    AddAccountRequest addAccountRequest = new AddAccountRequest();
    AddAccountResponse addAccountResponse;
    EditAccountInfoRequest editAccountInfoRequest = new EditAccountInfoRequest();

    @Test
    public void smokeEditAccountInfo() throws InterruptedException {
        AddAccountDataSet requestedAddAccountDataSet = app.generate().randomUser();
        addAccountResponse = addAccountRequest.addAccount(requestedAddAccountDataSet);

        EditAccountInfoDataSet editAccountInfoDataSet = new EditAccountInfoDataSet()
        .withFinancialInfo(new FinancialInfo()
                .withIsCreditUser(new IsCreditUser()
                        .withValue(true))
                .withAccountId(String.valueOf((addAccountResponse.account_id()))))
        .withLimitsInfo(new LimitsInfo()
                .withAccountId(String.valueOf((addAccountResponse.account_id())))
                .withFinancialLimits(new FinancialLimits()
                        .withCreditLimit(new CreditLimit().withValue("200"))));

        editAccountInfoRequest.editAccountInfo(addAccountResponse, editAccountInfoDataSet);

        assertTrue(app.db().getUserByName(requestedAddAccountDataSet.accountName().toLowerCase()).isCredit());
    }
}
