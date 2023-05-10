package model.cucumber.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.http.accounts.add.AddAccountDataSet;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;
import static tests.accounts.TestBase.app;
public class AddAccountDefinitions  extends DefinitionsBase {
    private World world;

    public AddAccountDefinitions(World world) {
        this.world = world;
    }

    @Given("Созданы данные аккаунта с длинной параметра account_name {string}")
    public void createAddAccountDataSetWithFixAccountNameLength(String accountNameLength) throws IOException {
        app.init();
        world.addAccountDataSet = new AddAccountDataSet()
                .withActive(true)
                .withApiKey(app.getProperty("papi.admin_api-key"))
                .withPassword(app.generate().randomPassword(15))
                .withEmail(app.generate().randomEmail())
                .withName(app.generate().randomString(parseInt(accountNameLength)));
    }

    @Given("Созданы данные аккаунта с длинной параметра account_password {string}")
    public void createAddAccountDataSetWithFixAccountPasswordLength(String accountPasswordLength) throws IOException {
        app.init();
        world.addAccountDataSet = new AddAccountDataSet()
                .withActive(true)
                .withApiKey(app.getProperty("papi.admin_api-key"))
                .withPassword(app.generate().randomPassword(parseInt(accountPasswordLength)))
                .withEmail(app.generate().randomEmail())
                .withName(app.generate().randomString(12));
    }

    @Given("Созданы данные аккаунта со значением параметра currency = {string}")
    public void createAccountDataSetWithFixCurrency(String currency) throws IOException {
        app.init();
        world.addAccountDataSet = new AddAccountDataSet()
                .withActive(true)
                .withApiKey(app.getProperty("papi.admin_api-key"))
                .withPassword(app.generate().randomPassword(10))
                .withEmail(app.generate().randomEmail())
                .withName(app.generate().randomString(12))
                .withCurrency(currency);
    }

    @Given("Создан аккаунт c с валидными данными")
    public void createAccountWithValidData() throws IOException {
        app.init();
        world.addAccountDataSet = new AddAccountDataSet()
                .withActive(true)
                .withApiKey(app.getProperty("papi.admin_api-key"))
                .withPassword(app.generate().randomPassword(15))
                .withEmail(app.generate().randomEmail())
                .withName(app.generate().randomString(12));

        world.addAccountResponse = addAccountRequest.addAccount(world.addAccountDataSet);
    }

    @When("Отправлен запрос на создание аккаунта с подготовленными данными")
    public void sendAddAccountRequest() {
        world.addAccountResponse = addAccountRequest.addAccount(world.addAccountDataSet);
    }

    @Then("Проверка успешности создания аккаунта: result = {string}, и занесения данных в БД")
    public void checkResultParamInResponse(String result) throws InterruptedException {
        assertThat(world.addAccountResponse.result()).isEqualTo(parseInt(result));
        if (parseInt(result) == 1) {
            int accountIdFromBd = app.db().getUserByName(world.addAccountDataSet).getId();
            int accountIdFromResponse = world.addAccountResponse.account_id();
            assertThat(accountIdFromBd).isEqualTo(accountIdFromResponse);
        }
    }
}
