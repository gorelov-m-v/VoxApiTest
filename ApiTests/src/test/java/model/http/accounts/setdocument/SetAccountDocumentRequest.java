package model.http.accounts.setdocument;

import model.constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import static io.restassured.RestAssured.given;
import static model.cucumber.definitions.DefinitionsBase.app;

public class SetAccountDocumentRequest {

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 15000));

    public SetAccountDocumentResponse setAccountDocument(AccountDocumentDataSet accountDocumentDataSet) {
        RestAssured.baseURI = app.getProperty("papi.host");
        return given()
                .config(config)
                .header("Content-type", "application/json")

                // Mandatory for 'INDIVIDUAL'
                .queryParam("account_id", accountDocumentDataSet.getAccountId())
                .queryParam("api_key", accountDocumentDataSet.getApiKey())
                .queryParam("legal_status", accountDocumentDataSet.getLegalStatus())
                .queryParam("esia_verified", accountDocumentDataSet.getEsiaVerified())
                .queryParam("individual_passport_series", accountDocumentDataSet.getIndividualPassportSeries())
                .queryParam("individual_passport_number", accountDocumentDataSet.getIndividualPassportNumber())
                .queryParam("individual_passport_issued_by", accountDocumentDataSet.getIndividualPassportIssued_by())
                .queryParam("individual_passport_issue_date", accountDocumentDataSet.getIndividualPassportIssueDate())
                .queryParam("individual_full_name", accountDocumentDataSet.getIndividualFullName())
                .queryParam("individual_birth_date", accountDocumentDataSet.getIndividualBirthDate())
                .queryParam("individual_registration_address", accountDocumentDataSet.getIndividualRegistrationAddress())
                .queryParam("individual_phone_number", accountDocumentDataSet.getIndividualPhoneNumber())
                .queryParam("document_delivery_address", accountDocumentDataSet.getDocumentDeliveryAddress())
                .queryParam("email", accountDocumentDataSet.getEmail())

                .when()
                .log()
                .all()
                .get(Paths.SET_ACCOUNT_DOCUMENT.toString())
                .then()
                .log()
                .all()
                .extract()
                .body()
                .as(SetAccountDocumentResponse.class);
    }
}
