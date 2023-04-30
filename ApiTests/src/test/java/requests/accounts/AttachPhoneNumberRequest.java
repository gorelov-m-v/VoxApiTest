package requests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import requests.model.AttachPhoneNumber;
import response.accounts.AttachPhoneNumberResponse;
import static io.restassured.RestAssured.given;

public class AttachPhoneNumberRequest {

    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 15000));

    public AttachPhoneNumberResponse attachPhoneNumber(AttachPhoneNumber attachPhoneNumber) {
        return given()
                .config(config)
                .header("Content-type", "application/json")
                // Mandatory
                .queryParam("account_id", attachPhoneNumber.getAccountId())
                .queryParam("api_key", attachPhoneNumber.getApiKey())
                .queryParam("country_code", attachPhoneNumber.getCountryCode())
                .queryParam("phone_category_name", attachPhoneNumber.getPhoneCategoryName())
                .queryParam("phone_region_id", attachPhoneNumber.getPhoneRegionId())
                // Optional
//                .queryParam("phone_number", attachPhoneNumber.getPhoneNumber())
                .queryParam("country_state", attachPhoneNumber.getCountryState())
                .queryParam("regulation_address_id", attachPhoneNumber.getRegulationAddressId())

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
