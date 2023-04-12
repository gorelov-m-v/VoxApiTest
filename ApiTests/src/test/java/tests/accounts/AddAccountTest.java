package tests.accounts;

import appmanager.DbHelper;
import constants.Paths;
import io.restassured.RestAssured;
import requests.model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import requests.accounts.AddAccountRequest;
import response.accounts.AddAccountResponse;
import utils.Generator;
import static org.assertj.core.api.Assertions.assertThat;


public class AddAccountTest {

    Paths paths = new Paths();
    Generator generate = new Generator();
    AddAccountRequest request = new AddAccountRequest();
    DbHelper db = new DbHelper();

    AddAccountResponse response;
    User user;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = paths.URL;
    }

    @DataProvider(name = "mandatoryParams")
    public Object[][] mandatoryParamsSet() {

        return new Object[][]{{ new User().withEmail(generate.randomEmail())
                                          .withName(generate.randomAccountName(15))
                                          .withPassword(generate.simplePassword).withApiKey(generate.api_key)
                                          .withActive(true), 1},
                              { new User().withName(generate.randomAccountName(15))
                                          .withPassword(generate.simplePassword).withApiKey(generate.api_key)
                                          .withActive(true), 0},
                              { new User().withEmail(generate.randomEmail())
                                          .withPassword(generate.simplePassword).withApiKey(generate.api_key)
                                          .withActive(true), 0},
                              { new User().withEmail(generate.randomEmail())
                                          .withName(generate.randomAccountName(15))
                                          .withApiKey(generate.api_key)
                                          .withActive(true), 0},
                              { new User().withEmail(generate.randomEmail())
                                          .withName(generate.randomAccountName(15))
                                          .withPassword(generate.simplePassword)
                                          .withActive(true), 0},
                              { new User().withEmail(generate.randomEmail())
                                          .withName(generate.randomAccountName(15))
                                          .withPassword(generate.simplePassword).withApiKey(generate.api_key), 0}};
    }

    @Test(dataProvider = "mandatoryParams")
    public void mandatoryParamsTest(User user, int result) {

        response = request.addAccount(user);

        assertThat(response.result()).isEqualTo(result);
    }

    @DataProvider(name = "accountNameLength")
    public static Object[][] nameLength() {

        return new Object[][]{{ 0},
                              { 2},
                              { 4},
                              { 5},
                              { 6},
                              {10},
                              {19},
                              {20},
                              {21},
                              {30}};
    }

    @Test(dataProvider = "accountNameLength")
    public void accountNameLengthTest(int accountNameLength) {

        user = new User().withEmail(generate.randomEmail()).withName(generate.randomAccountName(accountNameLength))
                         .withPassword(generate.simplePassword).withActive(true).withApiKey(generate.api_key);

        response = request.addAccount(user);

        if (response.result() == 1) {
            assertThat(response.account_id()).isNotNull();
            assertThat(response.api_key()).isNotNull();
            assertThat(response.billing_account_id()).isNotNull();
            assertThat(response.active()).isEqualTo(user.active());
        } else {
            assertThat(response.getError().getCode()).isEqualTo(113);
            assertThat(response.getError().getMsg()).isEqualTo("Account name must be at least 5 and up to 20 characters long.");
            assertThat(response.getError().getField_name()).isEqualTo("account_name");
        }
    }

    @DataProvider(name = "passwordLength")
    public static Object[][] passwordLength() {

        return new Object[][]{{ 7},
                              { 8},
                              { 9},
                              {14}};
    }

    @Test(dataProvider = "passwordLength")
    public void passwordLengthTest(int passwordLength) {

        user = new User().withEmail(generate.randomEmail()).withName(generate.randomAccountName(15))
                .withPassword(generate.randomPassword(passwordLength)).withActive(true).withApiKey(generate.api_key);

        response = request.addAccount(user);

        if (response.result() == 1) {
            assertThat(response.account_id()).isNotNull();
            assertThat(response.api_key()).isNotNull();
            assertThat(response.billing_account_id()).isNotNull();
            assertThat(response.active()).isEqualTo(user.active());
        } else {
            System.out.println(user.getAccount_password());
            assertThat(response.getError().getMsg()).isEqualTo("Password should be at least 8 characters long and contain uppercase characters (A-Z), lowercase characters (a-z), digits (0-9) and special characters (~!@#$%^&*_-+=`|\\(){}[]:;\"'<>,.?/)");
            assertThat(response.getError().getCode()).isEqualTo(464);

            assertThat(response.getError().getField_name()).isEqualTo("account_password");
        }
    }

    @DataProvider(name = "currenciesSet")
    public static Object[][] currenciesSet() {

        return new Object[][]{{"USD"},
                              {"RUR"},
                              {"EUR"},
                              {"KZT"}};
    }

    @Test(dataProvider = "currenciesSet")
    public void currenciesSetTests(String currency) {
        user = new User().withEmail(generate.randomEmail()).withName(generate.randomAccountName(15))
                         .withPassword(generate.simplePassword).withActive(true)
                         .withCurrency(currency).withApiKey(generate.api_key);

        response = request.addAccount(user);

        assertThat(db.getUserByName(user.accountName().toLowerCase()).getCurrency().getCode()).isEqualTo(user.getCurrency());
    }
}
