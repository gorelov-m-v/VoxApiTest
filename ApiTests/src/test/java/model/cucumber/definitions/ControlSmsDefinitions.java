package model.cucumber.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import model.http.sms.control.ControlSmsDataSet;
import java.io.IOException;

public class ControlSmsDefinitions extends DefinitionsBase {
    private World world;
    public ControlSmsDefinitions(World world) {
        this.world = world;
    }

    @Before
    public void setup() throws IOException {
        app.init();
    }

    @Given("Созданы данные для подключения купленному номеру поддержки SMS")
    public void createControlSmsDataSet() {
        world.controlSmsDataSet = new ControlSmsDataSet().withAccountId(world.addAccountResponse.account_id())
                .withApiKey(world.addAccountResponse.api_key())
                .withPhoneNumber(world.attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withCommand("enable");
    }

    @Given("Купленному мобильному номеру подключена поддержка SMS")
    public void turnOnSmsEnabled() {
        world.controlSmsDataSet = new ControlSmsDataSet().withAccountId(world.addAccountResponse.account_id())
                .withApiKey(world.addAccountResponse.api_key())
                .withPhoneNumber(world.attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withCommand("enable");

        world.universalResponse = controlSmsRequest.controlSms(world.controlSmsDataSet);
    }

    @When("Отправлен запрос подключения купленному номеру поддержки SMS")
    public void sendControlSmsRequest() {
        world.universalResponse = controlSmsRequest.controlSms(world.controlSmsDataSet);
    }
}
