package model.http.sms.received;

import model.constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import static io.restassured.RestAssured.given;
import static model.cucumber.definitions.DefinitionsBase.app;

public class ReceivedSMSRequest {

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 15000));

    public void receivedSms(ReceiverSmsHTTPDataSet receiverSmsHTTPDataSet) {
        RestAssured.baseURI = app.getProperty("sms_gw.host");
        given()
                .config(config)
                .header("Content-type", "application/json")
                // Mandatory
                .body(receiverSmsHTTPDataSet)

                .when()
                .log()
                .all()
                .post(Paths.RECEIVED_SMS.toString())
                .then()
                .log()
                .all();
    }
}
