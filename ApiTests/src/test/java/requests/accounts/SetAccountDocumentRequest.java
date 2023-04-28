package requests.accounts;

import constants.Paths;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import requests.model.AccountDocument;
import response.accounts.SetAccountDocumentResponse;
import static io.restassured.RestAssured.given;

public class SetAccountDocumentRequest {

    Paths paths = new Paths();

    RestAssuredConfig config = RestAssured.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000)
                    .setParam(CoreConnectionPNames.SO_TIMEOUT, 2000));

    public SetAccountDocumentResponse setAccountDocument(AccountDocument accountDocument) {
        return given()
                .config(config)
                .header("Content-type", "application/json")

                // Mandatory for 'INDIVIDUAL'
                .queryParam("account_id", accountDocument.getAccountId())
                .queryParam("api_key", accountDocument.getApiKey())
                .queryParam("legal_status", accountDocument.getLegalStatus())
                .queryParam("esia_verified", accountDocument.getEsiaVerified())
                .queryParam("individual_passport_series", accountDocument.getIndividualPassportSeries())
                .queryParam("individual_passport_number", accountDocument.getIndividualPassportNumber())
                .queryParam("individual_passport_issued_by", accountDocument.getIndividualPassportIssued_by())
                .queryParam("individual_passport_issue_date", accountDocument.getIndividualPassportIssueDate())
                .queryParam("individual_full_name", accountDocument.getIndividualFullName())
                .queryParam("individual_birth_date", accountDocument.getIndividualBirthDate())
                .queryParam("individual_registration_address", accountDocument.getIndividualRegistrationAddress())
                .queryParam("individual_phone_number", accountDocument.getIndividualPhoneNumber())
                .queryParam("document_delivery_address", accountDocument.getDocumentDeliveryAddress())
                .queryParam("email", accountDocument.getEmail())

                .when()
                .log()
                .all()
                .get(paths.setAccountDocument)
                .then()
                .log()
                .all()
                .extract()
                .body()
                .as(SetAccountDocumentResponse.class);
    }
}
