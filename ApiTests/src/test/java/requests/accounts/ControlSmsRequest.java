package requests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import requests.model.ControlSms;
import response.accounts.UniversalResponse;
import static io.restassured.RestAssured.given;

public class ControlSmsRequest {
    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 15000));

    public UniversalResponse ControlSms(ControlSms controlSms) {
        return given()
                .config(config)
                .header("Content-type", "application/json")
                // Mandatory
                .queryParam("account_id", controlSms.getAccountId())
                .queryParam("api_key", controlSms.getApiKey())
                .queryParam("phone_number", controlSms.getPhoneNumber())
                .queryParam("command", controlSms.getCommand())

                .when()
                .log()
                .all()
                .get(paths.controlSms)
                .then()
                .log()
                .all()
                .extract()
                .body()
                .as(UniversalResponse.class);
    }
}
