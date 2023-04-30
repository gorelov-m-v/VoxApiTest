package requests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import requests.model.ControlSmsDataSet;
import response.accounts.UniversalResponse;
import static io.restassured.RestAssured.given;

public class ControlSmsRequest {
    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 15000));

    public UniversalResponse ControlSms(ControlSmsDataSet controlSmsDataSet) {
        return given()
                .config(config)
                .header("Content-type", "application/json")
                // Mandatory
                .queryParam("account_id", controlSmsDataSet.getAccountId())
                .queryParam("api_key", controlSmsDataSet.getApiKey())
                .queryParam("phone_number", controlSmsDataSet.getPhoneNumber())
                .queryParam("command", controlSmsDataSet.getCommand())

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
