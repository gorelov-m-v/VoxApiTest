package model.cucumber.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import model.http.sms.send.SendSmsMessageDataSet;

import java.io.IOException;

import static tests.accounts.TestBase.app;

public class SendSmsMessageDefinitions extends DefinitionsBase {
    private World world;
    public SendSmsMessageDefinitions(World world) {
        this.world = world;
    }

    @Before
    public void setup() throws IOException {
        app.init();
    }

    @Given("Созданы данные для отправки SMS")
    public void createdValidSendSmsMessageDataSet() {
        world.sendSmsMessageDataSet =  new SendSmsMessageDataSet()
                .withAccountId(world.addAccountResponse.account_id())
                .withApiKey(world.addAccountResponse.api_key())
                .withSource(world.attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withDestination("32466902107")
                .withSmsBody(app.generate().randomString(20))
                .withStoreBody(true);
    }
    @When("Отправлен http запрос к платформе для отправки SMS")
    public void sendSmsMessageRequest() {
        world.sendSmsMessageResponse = sendSmsMessageRequest.sendSmsMessage(world.sendSmsMessageDataSet);
    }
}
