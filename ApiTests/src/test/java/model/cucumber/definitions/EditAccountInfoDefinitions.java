package model.cucumber.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.http.accounts.editinfo.EditAccountInfoDataSet;
import model.http.accounts.editinfo.dataset.*;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.accounts.TestBase.app;

public class EditAccountInfoDefinitions extends DefinitionsBase {
    private World world;
    public EditAccountInfoDefinitions(World world) {
        this.world = world;
    }
    @Before
    public void setup() throws IOException {
        app.init();
    }

    @Given("Созданы данные для изменения кредитного статуса и кредитного лимита аккаунта")
    public void createEditAccountInfoDataSet() throws IOException {
        world.editAccountInfoDataSet =  new EditAccountInfoDataSet()
                .withFinancialInfo(new FinancialInfo()
                        .withIsCreditUser(new IsCreditUser()
                                .withValue(true))
                        .withAccountId(String.valueOf((world.addAccountResponse.account_id()))))
                .withLimitsInfo(new LimitsInfo()
                        .withAccountId(String.valueOf((world.addAccountResponse.account_id())))
                        .withFinancialLimits(new FinancialLimits()
                                .withCreditLimit(new CreditLimit().withValue("200.1123345"))));
    }

    @Given("Созданы данные для изменения кредитного статуса и кредитного лимита в {string}$ аккаунта")
    public void createEditAccountInfoDataSetWithFixCreditLimit(String creditLimit) {
        world.editAccountInfoDataSet =  new EditAccountInfoDataSet()
                .withFinancialInfo(new FinancialInfo()
                        .withIsCreditUser(new IsCreditUser()
                                .withValue(true))
                        .withAccountId(String.valueOf((world.addAccountResponse.account_id()))))
                .withLimitsInfo(new LimitsInfo()
                        .withAccountId(String.valueOf((world.addAccountResponse.account_id())))
                        .withFinancialLimits(new FinancialLimits()
                                .withCreditLimit(new CreditLimit().withValue(creditLimit))));
    }

    @When("Отправлен запрос на изменение данных аккаунта")
    public void sendEditAccountInfoRequest() throws IOException {
        editAccountInfoRequest.editAccountInfo(world.addAccountResponse, world.editAccountInfoDataSet);
    }

    @Then("Проверка занесения изменений кредитных статуса и лимита аккаунта в БД")
    public void checkAccountCreditStatusAndLimit() throws InterruptedException, IOException {
        assertThat(app.db().getUserByName(world.addAccountDataSet).isCredit()).isTrue();
        assertThat(app.db().getBillingInfo(world.addAccountResponse).getCredit_limit().stripTrailingZeros().toPlainString())
                .isEqualTo(world.editAccountInfoDataSet.getLimitsInfo().getFinancialLimits().getCreditLimit().getValue());
    }
}
