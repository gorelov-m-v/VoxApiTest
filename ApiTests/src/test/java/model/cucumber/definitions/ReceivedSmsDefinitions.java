package model.cucumber.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.http.sms.received.ReceiverSmsHTTPDataSet;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReceivedSmsDefinitions extends DefinitionsBase{

    private World world;
    public ReceivedSmsDefinitions(World world) {
        this.world = world;
    }

    @Before
    public void setup() throws IOException {
        app.init();
    }

    @Given("Созданы данные для имитации получения SMS")
    public void createReceiveSmsDataSet() {
        world.receivedSmsHTTPDataSet = new ReceiverSmsHTTPDataSet()
                .withSrcNumber(app.generate().randomString(11))
                .withDstNumber(world.attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withContent(app.generate().randomString(15));
        System.out.println(world.attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number());

    }
    @When("Отправлен http запрос к Sms_gw, для имитации получения смс")
    public void sendReceiveSmsRequest() {
        receivedSMSRequest.receivedSms(world.receivedSmsHTTPDataSet);
    }

    @Then("Проверка наличия SMS у купленного номера в таблице sms_history")
    public void checkSmsInSmsHistoryTable() throws InterruptedException {
        world.smsHistory = app.db().getSmsByDestNumber(world.attachPhoneNumberResponse);

        assertThat(world.smsHistory).isNotNull();
    }

}
