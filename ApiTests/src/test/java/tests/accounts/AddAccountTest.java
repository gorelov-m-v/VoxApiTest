package tests.accounts;

import Database.model.DbHelper;
import constants.Paths;
import io.restassured.RestAssured;
import model.User;
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
    AddAccountResponse response;
    DbHelper db = new DbHelper();
    User user;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = paths.URL;
    }

    @Test
    public void AddAccountSmoke() {
        user = new User().withEmail(generate.randomEmail()).withName(generate.randomAccountName(20))
                .withPassword(generate.password).withActive(true)
                .withCurrency(generate.CURRENCY[0]).withInitBalance(generate.randomValue(1000, 2000))
                .withIsTrial(false).withApiKey(generate.api_key);


        response = request.addAccount(user);

        assertThat(user.accountName().toLowerCase()).isEqualTo(db.getUserByName(user.accountName().toLowerCase()).username());
        assertThat(response.account_id()).isEqualTo(db.getUserByName(user.accountName().toLowerCase()).getId());
    }

    @DataProvider(name = "accountNameLength")
    public static Object[][] passwordLength() {

        return new Object[][]{{0},
                              {2},
                              {4},
                              {5},
                              {6},
                              {10},
                              {19},
                              {20},
                              {21},
                              {30}};
    }

    @Test(dataProvider = "accountNameLength")
    public void accountNameLengthTest(int accountNameLength) {

        user = new User().withEmail(generate.randomEmail()).withName(generate.randomAccountName(accountNameLength))
                .withPassword(generate.password).withActive(true)
                .withCurrency(generate.CURRENCY[0]).withInitBalance(generate.randomValue(1000, 2000))
                .withIsTrial(false).withApiKey(generate.api_key);

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
                         .withPassword(generate.password).withActive(true)
                         .withCurrency(currency).withInitBalance(generate.randomValue(1000, 2000))
                         .withIsTrial(false).withApiKey(generate.api_key);


    }
}
