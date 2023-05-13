package model.cucumber.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import model.http.phonenumbers.attach.AttachPhoneNumberDataSet;
import java.io.IOException;

public class AttachPhoneNumberDefinitions extends DefinitionsBase {
    private World world;
    public AttachPhoneNumberDefinitions(World world) {
        this.world = world;
    }

    @Before
    public void setup() throws IOException {
        app.init();
    }

    @Given("Созданы данные для покупки номера телефона")
    public void createValidAttachPhoneNumberDataSet() {
        world.attachPhoneNumberDataSet = new AttachPhoneNumberDataSet().withAccountId(world.addAccountResponse.account_id())
                .withApiKey(world.addAccountResponse.api_key())
                .withCountryCode("BE")
                .withPhoneRegionId("20560")
                .withPhoneCategoryName("MOBILE");
    }

    @Given("Аккаунтом куплен мобильный номер")
    public void buyMobileNumberWithValidData() {
        world.attachPhoneNumberDataSet = new AttachPhoneNumberDataSet().withAccountId(world.addAccountResponse.account_id())
                .withApiKey(world.addAccountResponse.api_key())
                .withCountryCode("BE")
                .withPhoneRegionId("20560")
                .withPhoneCategoryName("MOBILE");

        world.attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(world.attachPhoneNumberDataSet);
    }

    @When("Отправлен запрос на покупку номера телефона")
    public void sendAttachPhoneNumberRequest() {
        world.attachPhoneNumberResponse = attachPhoneNumberRequest.attachPhoneNumber(world.attachPhoneNumberDataSet);
    }
}
