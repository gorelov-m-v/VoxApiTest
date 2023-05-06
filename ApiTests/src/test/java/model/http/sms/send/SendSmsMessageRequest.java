package model.http.sms.send;

import model.constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import static io.restassured.RestAssured.given;
import static tests.accounts.TestBase.app;

public class SendSmsMessageRequest {
    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 30000));

    public SendSmsMessageResponse sendSmsMessage(SendSmsMessageDataSet sendSmsMessageDataSet) {
        RestAssured.baseURI = app.getProperty("papi.host");
        return given()
                .config(config)
                .header("Content-type", "application/json")
                // Mandatory
                .queryParam("account_id", sendSmsMessageDataSet.getAccountId())
                .queryParam("api_key", sendSmsMessageDataSet.getApiKey())
                .queryParam("destination", sendSmsMessageDataSet.getDestination())
                .queryParam("source", sendSmsMessageDataSet.getSource())
                .queryParam("sms_body", sendSmsMessageDataSet.getSmsBody())
                .queryParam("store_body", sendSmsMessageDataSet.isStoreBody())

                .when()
                .log()
                .all()
                .get(paths.sendSmsMessage)
                .then()
                .log()
                .all()
                .extract()
                .body()
                .as(SendSmsMessageResponse.class);
    }
}
