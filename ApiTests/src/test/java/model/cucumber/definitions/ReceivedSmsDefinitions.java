package model.cucumber.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import model.http.sms.received.ReceivedSmsMQDataSet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import static model.cucumber.definitions.DefinitionsBase.app;

public class ReceivedSmsDefinitions {

    private World world;
    public ReceivedSmsDefinitions(World world) {
        this.world = world;
    }

    @Before
    public void setup() throws IOException {
        app.init();
    }

    @When("Имитирована доставка SMS сообщением в очередь")
    public void sendReceivedRabbitImitation() throws InterruptedException, IOException, TimeoutException {

        world.receivedSmsMQDataSet = new ReceivedSmsMQDataSet()
                .withSourceNumber(app.generate().randomString(11))
                .withDestinationNumber(world.attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withUuid(UUID.randomUUID().toString())
                .withMessage(app.generate().randomString(20))
                .withFragmentsCount(1)
                .withReceivedDate(LocalDate.now().toString());


        String message = app.generate().generateJsonFromObject(world.smsSendingInfoDataSet);
        System.out.println(message);

        app.mqp().publish(app.getProperty("rabbitmq.exchange.sms"),
                app.getProperty("rabbitmq.routing-key.receiveSms"),
                message);
    }

}
