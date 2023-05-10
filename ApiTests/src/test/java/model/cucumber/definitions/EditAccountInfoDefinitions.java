package model.cucumber.definitions;

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

    @Given("Созданы данные для изменения статуса и кредитного лимита аккаунта")
    public void createEditAccountInfoDataSet() throws IOException {
        app.init();
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

    @When("Отправлен запрос на изменение данных аккаунта")
    public void sendEditAccountInfoRequest() throws IOException {
        app.init();
        editAccountInfoRequest.editAccountInfo(world.addAccountResponse, world.editAccountInfoDataSet);
    }

    @Then("Проверка занесения изменений кредитных статуса и лимита аккаунта в БД")
    public void checkAccountCreditStatusAndLimit() throws InterruptedException, IOException {
        app.init();
        assertThat(app.db().getUserByName(world.addAccountDataSet).isCredit()).isTrue();
        assertThat(app.db().getBillingInfo(world.addAccountResponse).getCredit_limit().stripTrailingZeros().toPlainString())
                .isEqualTo(world.editAccountInfoDataSet.getLimitsInfo().getFinancialLimits().getCreditLimit().getValue());
    }
}
