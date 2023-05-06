package tests.accounts;

import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.add.AddAccountRequest;
import model.http.accounts.add.AddAccountResponse;
import model.http.accounts.editinfo.EditAccountInfoDataSet;
import model.http.accounts.editinfo.EditAccountInfoRequest;
import model.http.accounts.editinfo.dataset.*;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
                        .withCreditLimit(new CreditLimit().withValue("200.1123345"))));

        editAccountInfoRequest.editAccountInfo(addAccountResponse, editAccountInfoDataSet);

        assertThat(app.db().getUserByName(requestedAddAccountDataSet).isCredit()).isTrue();
        assertThat(app.db().getBillingInfo(addAccountResponse).getCredit_limit().stripTrailingZeros().toPlainString())
                .isEqualTo(editAccountInfoDataSet.getLimitsInfo().getFinancialLimits().getCreditLimit().getValue());
    }
}
