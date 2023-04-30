package requests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import requests.model.UserDataSet;
import org.apache.http.params.CoreConnectionPNames;
import response.accounts.AddAccountResponse;
import static io.restassured.RestAssured.given;

// VoxDocs  https://voximplant.com/docs/references/httpapi/accounts#addaccount
public class AddAccountRequest {
    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 2000));
    public AddAccountResponse addAccount(UserDataSet userDataSet) {
        return given()
                    .config(config)
                    .header("Content-type", "application/json")
                    // Mandatory
                    .queryParam("account_name", userDataSet.accountName())
                    .queryParam("account_password", userDataSet.getAccount_password())
                    .queryParam("account_email", userDataSet.getAccount_email())
                    .queryParam("api_key", userDataSet.getApiKey())
                    .queryParam("active", userDataSet.active())
                    // Mandatory for parent account
                    .queryParam("parent_account_id", userDataSet.getParent_account_id())
                    .queryParam("parent_account_name", userDataSet.getParent_account_name())
                    .queryParam("parent_account_email", userDataSet.getParent_account_email())
                    .queryParam("parent_account_api_key", userDataSet.getParent_account_api_key())
                    .queryParam("parent_account_password", userDataSet.getParent_account_password())
                    // Optional
                    .queryParam("currency", userDataSet.getCurrency())
                    .queryParam("is_trial", userDataSet.is_trial())
                    .queryParam("init_balance", userDataSet.getInitBalance())
                    .queryParam("min_balance_to_notify", userDataSet.getMin_balance_to_notify())
                    .queryParam("account_custom_data", userDataSet.getAccount_custom_data())
                    .queryParam("account_first_name", userDataSet.getAccount_first_name())
                    .queryParam("account_last_name", userDataSet.getAccount_last_name())
                    .queryParam("account_notifications", userDataSet.isAccount_notifications())
                    .queryParam("tariff_changing_notifications", userDataSet.isTariff_changing_notifications())
                    .queryParam("news_notifications", userDataSet.isNews_notifications())
                    .queryParam("language_code", userDataSet.getLanguage_code())
                    .queryParam("location", userDataSet.getLocation())
                    .queryParam("record_storage_id", userDataSet.getRecord_storage_id())
                    .queryParam("record_storage_name", userDataSet.getRecord_storage_name())

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
