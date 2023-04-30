package tests.accounts;

import http.model.accounts.add.UserDataSet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import http.model.accounts.add.AddAccountRequest;
import http.model.accounts.add.AddAccountResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class AddAccountTests extends TestBase {

    AddAccountRequest request = new AddAccountRequest();
    AddAccountResponse response;
    UserDataSet userDataSet;

    @DataProvider(name = "mandatoryParams")
    public Object[][] mandatoryParamsSet() {

        return new Object[][]{{ new UserDataSet().withEmail(app.generate().randomEmail())
                                          .withName(app.generate().randomAccountName(15))
                                          .withPassword(app.generate().simplePassword).withApiKey(app.getProperty("papi.admin_api-key"))
                                          .withActive(true), 1},
                              { new UserDataSet().withName(app.generate().randomAccountName(15))
                                          .withPassword(app.generate().simplePassword).withApiKey(app.getProperty("papi.admin_api-key"))
                                          .withActive(true), 0},
                              { new UserDataSet().withEmail(app.generate().randomEmail())
                                          .withPassword(app.generate().simplePassword).withApiKey(app.getProperty("papi.admin_api-key"))
                                          .withActive(true), 0},
                              { new UserDataSet().withEmail(app.generate().randomEmail())
                                          .withName(app.generate().randomAccountName(15))
                                          .withApiKey(app.getProperty("papi.admin_api-key"))
                                          .withActive(true), 0},
                              { new UserDataSet().withEmail(app.generate().randomEmail())
                                          .withName(app.generate().randomAccountName(15))
                                          .withPassword(app.generate().simplePassword)
                                          .withActive(true), 0},
                              { new UserDataSet().withEmail(app.generate().randomEmail())
                                          .withName(app.generate().randomAccountName(15))
                                          .withPassword(app.generate().simplePassword).withApiKey(app.getProperty("papi.admin_api-key")), 0}};
    }

    @Test(dataProvider = "mandatoryParams")
    public void mandatoryParamsTest(UserDataSet userDataSet, int result) {

        response = request.addAccount(userDataSet);

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

        userDataSet = new UserDataSet().withEmail(app.generate().randomEmail()).withName(app.generate().randomAccountName(accountNameLength))
                         .withPassword(app.generate().simplePassword).withActive(true).withApiKey(app.getProperty("papi.admin_api-key"));

        response = request.addAccount(userDataSet);

        if (response.result() == 1) {
            assertThat(response.account_id()).isNotNull();
            assertThat(response.api_key()).isNotNull();
            assertThat(response.billing_account_id()).isNotNull();
            assertThat(response.active()).isEqualTo(userDataSet.active());
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

        userDataSet = new UserDataSet().withEmail(app.generate().randomEmail()).withName(app.generate().randomAccountName(15))
                .withPassword(app.generate().randomPassword(passwordLength)).withActive(true).withApiKey(app.getProperty("papi.admin_api-key"));

        response = request.addAccount(userDataSet);

        if (response.result() == 1) {
            assertThat(response.account_id()).isNotNull();
            assertThat(response.api_key()).isNotNull();
            assertThat(response.billing_account_id()).isNotNull();
            assertThat(response.active()).isEqualTo(userDataSet.active());
        } else {
            System.out.println(userDataSet.getAccount_password());
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
        userDataSet = new UserDataSet().withEmail(app.generate().randomEmail()).withName(app.generate().randomAccountName(15))
                         .withPassword(app.generate().simplePassword).withActive(true)
                         .withCurrency(currency).withApiKey(app.getProperty("papi.admin_api-key"));

        response = request.addAccount(userDataSet);

        assertThat(app.db().getUserByName(userDataSet.accountName().toLowerCase()).getCurrency().getCode()).isEqualTo(userDataSet.getCurrency());
//        System.out.println(app.db().getUserByName(user.accountName().toLowerCase()).getPassword());
//        System.out.println(app.db().getUserByName(user.accountName().toLowerCase()).getActivated());
//        LocalDate now = now();
//        System.out.println(now);
    }
}
