package http.model.accounts.editinfo;

import constants.Paths;
import http.model.accounts.add.AddAccountResponse;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import static io.restassured.RestAssured.given;
import static tests.accounts.TestBase.app;

public class EditAccountInfoRequest {

    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 15000));

    public void editAccountInfo(AddAccountResponse addAccountResponse, EditAccountInfoDataSet editAccountInfoDataSet) {
        RestAssured.baseURI = app.getProperty("bo.envoy.host");
        given()
                .config(config)
                .header("Content-type", "application/json")
                // Mandatory
                .body(editAccountInfoDataSet)

                .when()
                .log()
                .all()
                .post(paths.getEditAccountInfoPath(addAccountResponse))
                .then()
                .log()
                .all();
    }
}
