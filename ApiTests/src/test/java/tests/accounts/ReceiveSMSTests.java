package tests.accounts;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveSMSTests extends TestBase{

    @Test
    public void receiveSMSByRabbitMessage() throws IOException, TimeoutException {
        String message = "{\n" +
                "    \"source_number\":\"89032530778\",\n" +
                "    \"destination_number\":\"32466901718\",\n" +
                "    \"uuid\":[\"d50eee8e-f343-47f6-8fa6-322fa7b78856\"],\n" +
                "    \"message\":\"la-la\",\n" +
                "    \"fragments_count\":1,\n" +
                "    \"received_date\":\"2023-02-16 00:00:00\"\n" +
                "}";

        app.p2p().publish(app.getProperty("rabbitmq.exchange.sms"),
                          app.getProperty("rabbitmq.routing-key.receiveSms"),
                          message);

    }
}