package http.model.accounts.add;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import static io.restassured.RestAssured.given;
import static tests.accounts.TestBase.app;

// VoxDocs  https://voximplant.com/docs/references/httpapi/accounts#addaccount
public class AddAccountRequest {
    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000));
    public AddAccountResponse addAccount(AddAccountDataSet addAccountDataSet) {
        RestAssured.baseURI = app.getProperty("papi.host");
        return given()
                    .config(config)
                    .header("Content-type", "application/json")
                    // Mandatory
                    .queryParam("account_name", addAccountDataSet.accountName())
                    .queryParam("account_password", addAccountDataSet.getAccount_password())
                    .queryParam("account_email", addAccountDataSet.getAccount_email())
                    .queryParam("api_key", addAccountDataSet.getApiKey())
                    .queryParam("active", addAccountDataSet.active())
                    // Mandatory for parent account
                    .queryParam("parent_account_id", addAccountDataSet.getParent_account_id())
                    .queryParam("parent_account_name", addAccountDataSet.getParent_account_name())
                    .queryParam("parent_account_email", addAccountDataSet.getParent_account_email())
                    .queryParam("parent_account_api_key", addAccountDataSet.getParent_account_api_key())
                    .queryParam("parent_account_password", addAccountDataSet.getParent_account_password())
                    // Optional
                    .queryParam("currency", addAccountDataSet.getCurrency())
                    .queryParam("is_trial", addAccountDataSet.is_trial())
                    .queryParam("init_balance", addAccountDataSet.getInitBalance())
                    .queryParam("min_balance_to_notify", addAccountDataSet.getMin_balance_to_notify())
                    .queryParam("account_custom_data", addAccountDataSet.getAccount_custom_data())
                    .queryParam("account_first_name", addAccountDataSet.getAccount_first_name())
                    .queryParam("account_last_name", addAccountDataSet.getAccount_last_name())
                    .queryParam("account_notifications", addAccountDataSet.isAccount_notifications())
                    .queryParam("tariff_changing_notifications", addAccountDataSet.isTariff_changing_notifications())
                    .queryParam("news_notifications", addAccountDataSet.isNews_notifications())
                    .queryParam("language_code", addAccountDataSet.getLanguage_code())
                    .queryParam("location", addAccountDataSet.getLocation())
                    .queryParam("record_storage_id", addAccountDataSet.getRecord_storage_id())
                    .queryParam("record_storage_name", addAccountDataSet.getRecord_storage_name())

                    .when()
                    .log()
                    .all()
                    .get(paths.addAccount)
                    .then()
                    .log()
                    .all()
                    .extract()
                    .body()
                    .as(AddAccountResponse.class);
        }
    }
