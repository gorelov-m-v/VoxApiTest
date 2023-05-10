package model.cucumber.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import model.http.accounts.setdocument.AccountDocumentDataSet;
import model.http.accounts.setdocument.SetAccountDocumentRequest;
import java.time.LocalDate;
import static tests.accounts.TestBase.app;

public class SetAccountDocumentDefinitions  extends DefinitionsBase {
    private World world;
    public SetAccountDocumentDefinitions(World world) {
        this.world = world;
    }

    @Given("Созданы данные для верификации аккаунта")
    public void createValidSetAccountDocumentDataSet() {
        world.accountDocumentDataSet =  new AccountDocumentDataSet().withAccountId(world.addAccountResponse.account_id())
                .withApiKey(app.getProperty("papi.admin_api-key"))
                .withLegalStatus("individual")
                .withEsiaVerified(true)
                .withIndividualPassportSeries(1222)
                .withIndividualPassportNumber(555667)
                .withIndividualPassportIssuedBy("sdadasd")
                .withIndividualPassportIssueDate(LocalDate.now().toString())
                .withIndividualFullName("asdasdasd")
                .withIndividualBirthDate(LocalDate.now().toString())
                .withIndividualRegistrationAddress("dasdasd")
                .withIndividualPhoneNumber("79032530778")
                .withDocumentDeliveryAddress("sadasdasdasd")
                .withEmail("mail123123dd@mail.ru");
    }
    @When("Отправлен запрос на создание верификации аккаунта")
    public void sendSetAccountDocumentRequest() {
        world.setAccountDocumentResponse = setAccountDocumentRequest.setAccountDocument(world.accountDocumentDataSet);
    }

}
