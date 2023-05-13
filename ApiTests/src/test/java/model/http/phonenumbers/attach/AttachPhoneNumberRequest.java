package model.http.phonenumbers.attach;

import model.constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import static io.restassured.RestAssured.given;
import static model.cucumber.definitions.DefinitionsBase.app;

public class AttachPhoneNumberRequest {

    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 30000));

    public AttachPhoneNumberResponse attachPhoneNumber(AttachPhoneNumberDataSet attachPhoneNumberDataSet) {
        RestAssured.baseURI = app.getProperty("papi.host");
        return given()
                .config(config)
                .header("Content-type", "application/json")
                // Mandatory
                .queryParam("account_id", attachPhoneNumberDataSet.getAccountId())
                .queryParam("api_key", attachPhoneNumberDataSet.getApiKey())
                .queryParam("country_code", attachPhoneNumberDataSet.getCountryCode())
                .queryParam("phone_category_name", attachPhoneNumberDataSet.getPhoneCategoryName())
                .queryParam("phone_region_id", attachPhoneNumberDataSet.getPhoneRegionId())
                // Optional
//                .queryParam("phone_number", attachPhoneNumber.getPhoneNumber())
                .queryParam("country_state", attachPhoneNumberDataSet.getCountryState())
                .queryParam("regulation_address_id", attachPhoneNumberDataSet.getRegulationAddressId())

                .when()
                .log()
                .all()
                .get(paths.attachPhoneNumber)
                .then()
                .log()
                .all()
                .extract()
                .body()
                .as(AttachPhoneNumberResponse.class);
    }
}
