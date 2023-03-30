package requests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import model.User;
import org.apache.http.params.CoreConnectionPNames;
import response.accounts.AddAccountResponse;

import static io.restassured.RestAssured.given;


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
                .queryParam("account_name", user.accountName())
                .queryParam("account_password", user.getAccount_password())
                .queryParam("account_email", user.getAccount_email())
                .queryParam("api_key", user.getApiKey())
                .queryParam("currency", user.getCurrency())
                .queryParam("is_trial", user.is_trial())
                .queryParam("active", user.active())
                .queryParam("init_balance", user.getInitBalance())
                .queryParam("is_trial", user.is_trial())
                .when()
                .get(paths.addAccount)
                .then()
                .extract()
                .body()
                .as(AddAccountResponse.class);
    }
}
