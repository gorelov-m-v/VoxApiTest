package requests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import requests.model.User;
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

    public AddAccountResponse addAccount(User user) {
        return given()
                    .config(config)
                    .header("Content-type", "application/json")
                    // Mandatory
                    .queryParam("account_name", user.accountName())
                    .queryParam("account_password", user.getAccount_password())
                    .queryParam("account_email", user.getAccount_email())
                    .queryParam("api_key", user.getApiKey())
                    .queryParam("active", user.active())
                    // Mandatory for parent account
                    .queryParam("getParent_account_id", user.getParent_account_id())
                    .queryParam("parent_account_name", user.getParent_account_name())
                    .queryParam("parent_account_email", user.getParent_account_email())
                    .queryParam("parent_account_api_key", user.getParent_account_api_key())
                    .queryParam("parent_account_password", user.getParent_account_password())
                    // Optional
                    .queryParam("currency", user.getCurrency())
                    .queryParam("is_trial", user.is_trial())
                    .queryParam("init_balance", user.getInitBalance())
                    .queryParam("min_balance_to_notify", user.getMin_balance_to_notify())
                    .queryParam("account_custom_data", user.getAccount_custom_data())
                    .queryParam("account_first_name", user.getAccount_first_name())
                    .queryParam("account_last_name", user.getAccount_last_name())
                    .queryParam("account_notifications", user.isAccount_notifications())
                    .queryParam("tariff_changing_notifications", user.isTariff_changing_notifications())
                    .queryParam("news_notifications", user.isNews_notifications())
                    .queryParam("language_code", user.getLanguage_code())
                    .queryParam("location", user.getLocation())
                    .queryParam("record_storage_id", user.getRecord_storage_id())
                    .queryParam("record_storage_name", user.getRecord_storage_name())

                    .when()
                    .get(paths.addAccount)
                    .then()
                    .extract()
                    .body()
                    .as(AddAccountResponse.class);
        }
    }
