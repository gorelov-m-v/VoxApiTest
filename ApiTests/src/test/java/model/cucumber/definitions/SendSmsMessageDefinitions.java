package model.cucumber.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.http.sms.send.SendSmsMessageDataSet;
import model.http.sms.send.SmsSendingInfoDataSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Given("Составлено сообщение для имитации доставки")
    public void createdSmsSendingInfoMessage() throws InterruptedException {
        List<String> transaction_id = new ArrayList<>();
        transaction_id.add(app.generate().randomString(10));
        world.smsHistory = app.db().getSms(world.sendSmsMessageResponse);

        world.smsSendingInfoDataSet =  new SmsSendingInfoDataSet()
                .withMessage("OK")
                .withTransaction_id(transaction_id)
                .withUuid(world.smsHistory.getUuid())
                .withFragments_count(1)
                .withCode(0);

        System.out.println(world.smsSendingInfoDataSet.getTransactionId());
    }

    @When("Отправлен http запрос к платформе для отправки SMS")
    public void sendSmsMessageRequest() {
        world.sendSmsMessageResponse = sendSmsMessageRequest.sendSmsMessage(world.sendSmsMessageDataSet);
    }

    @When("Имитирована доставка SMS сообщением в очередь")
    public void sendSmsInfoOkImitation() throws InterruptedException, IOException, TimeoutException {
        List<String> transaction_id = new ArrayList<>();
        transaction_id.add(app.generate().randomString(10));
        world.smsHistory = app.db().getSms(world.sendSmsMessageResponse);

        world.smsSendingInfoDataSet =  new SmsSendingInfoDataSet()
                .withMessage("OK")
                .withTransaction_id(transaction_id)
                .withUuid(world.smsHistory.getUuid())
                .withFragments_count(1)
                .withCode(0);

        String message = app.generate().generateJsonFromObject(world.smsSendingInfoDataSet);
        System.out.println(message);

        app.mqp().publish(app.getProperty("rabbitmq.exchange.sms"),
                app.getProperty("rabbitmq.routing-key.smsSendingInfo"),
                message);
    }

    @Then("Проверка наличия реальной транзакции, созданной после доставки SMS")
    public void checkRealTransactionInSmsHistory() throws InterruptedException {
        assertThat(app.db().getSms(world.sendSmsMessageResponse).gettransaction_id()).isNotNull();
    }

    @Then("Проверка наличия SMS в таблице sms_history")
    public void checkSmsInSmsHistoryTable() throws InterruptedException {
        world.smsHistory = app.db().getSms(world.sendSmsMessageResponse);

        assertThat(world.smsHistory).isNotNull();
    }
    @Then("Проверка наличия отложенной транзакции {string} в таблице deferred_transactions")
    public void checkDeferredTransaction(String expectedResult) throws InterruptedException {
        String actualResult;
        Integer deferredTransaction;

        world.smsHistory = app.db().getSms(world.sendSmsMessageResponse);
        try {
            deferredTransaction = app.db().getDeferredTransactions(world.smsHistory).getTransaction_id();
        } catch (NullPointerException e) {
            deferredTransaction = null;
        }

        if(deferredTransaction != null) {
            actualResult = "1";
        } else {
            actualResult = "0";
        }
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
