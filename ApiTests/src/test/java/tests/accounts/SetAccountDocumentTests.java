package tests.accounts;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requests.accounts.SetAccountDocumentRequest;
import requests.model.AccountDocument;
import response.accounts.SetAccountDocumentResponse;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.util.DateUtil.now;

public class SetAccountDocumentTests extends TestBase {

    SetAccountDocumentRequest request = new SetAccountDocumentRequest();
    SetAccountDocumentResponse response;


    @BeforeMethod
    public void setUp() throws IOException {
        app.init();
        RestAssured.baseURI = app.getProperty("papi.host");
    }

    @Test
    public void smoke() {

        LocalDate now = LocalDate.now();
        System.out.println(now);
        AccountDocument accountDocument = new AccountDocument().withAccountId(527350)
                .withApiKey("sasa").withLegalStatus("individual").withEsiaVerified(true)
                .withIndividualPassportSeries(1222).withIndividualPassportNumber(555667)
                .withIndividualPassportIssuedBy("sdadasd").withIndividualPassportIssueDate(now.toString())
                .withIndividualFullName("asdasdasd").withIndividualBirthDate(now.toString())
                .withIndividualRegistrationAddress("dasdasd").withIndividualPhoneNumber("79032530778")
                .withDocumentDeliveryAddress("sadasdasdasd").withEmail("mail123123dd@mail.ru");

        response = request.setAccountDocument(accountDocument);


    }
}
