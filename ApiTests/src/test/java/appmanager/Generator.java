package appmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.database.SmsHistory;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import model.http.sms.received.ReceivedSmsMQDataSet;
import java.util.*;
import com.rabbitmq.tools.json.JSONWriter;
import org.json.JSONObject;

public class Generator extends HelperBase {
    Random random = new Random();
    Gson gson = new Gson();
    JSONWriter rabbitmqJson = new JSONWriter();



    public Generator(ApplicationManager app) {
        super(app);
    }

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "~!@#$%^&*_-+=`|\\(){}[]:;\"'<>,.?/";
    public static final String ALPHANUMERIC_AND_SPECIAL_SYMBOLS = UPPER + LOWER + DIGITS + SPECIAL;

    public String randomString(int length) {
        String combination = LOWER + UPPER;
        char[] string = new char[length];
        for(int i = 0; i < length; i++) {
            string[i] = combination.charAt(random.nextInt(combination.length()));
        }
        return new String(string);
    }

    public String randomEmail() {
        String suffix = "@testmail.ru";
        String randomEmail = randomString(20) + suffix;
        return randomEmail.toLowerCase();
    }

    public String randomPassword(int length) {
        List<Character> chars = new ArrayList<>(length);
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        for (int i = 0; i < length; i++) {
            if (!hasUpper) {
                chars.add(UPPER.charAt(RandomUtils.nextInt(0, UPPER.length())));
                hasUpper = true;
            } else if (!hasLower) {
                chars.add(LOWER.charAt(RandomUtils.nextInt(0, LOWER.length())));
                hasLower = true;
            } else if (!hasNumber) {
                chars.add(DIGITS.charAt(RandomUtils.nextInt(0, DIGITS.length())));
                hasNumber = true;
            } else if (!hasSpecial) {
                chars.add(SPECIAL.charAt(RandomUtils.nextInt(0, SPECIAL.length())));
                hasSpecial = true;
            } else {
                chars.add(ALPHANUMERIC_AND_SPECIAL_SYMBOLS.charAt(RandomUtils.nextInt(0, ALPHANUMERIC_AND_SPECIAL_SYMBOLS.length())));
            }
        }
        Collections.shuffle(chars);
        return StringUtils.join(chars, "");
    }

    public int randomValue(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public String randomReceivedSmsMQDataSet(ReceivedSmsMQDataSet receivedSmsMQDataSet) {
        return String.format("{\n" +
                "    \"source_number\":\"%s\",\n" +
                "    \"destination_number\":\"%s\",\n" +
                "    \"uuid\":[\"%s\"],\n" +
                "    \"message\":\"%s\",\n" +
                "    \"fragments_count\":%s,\n" +
                "    \"received_date\":\"%s\"\n" +
                "}", receivedSmsMQDataSet.getSourceNumber()
                   , receivedSmsMQDataSet.getDestinationNumber()
                   , receivedSmsMQDataSet.getUuid()
                   , receivedSmsMQDataSet.getMessage()
                   , receivedSmsMQDataSet.getFragmentsCount()
                   , receivedSmsMQDataSet.getReceivedDate());
    }

    public String randomSmsSendingInfoMQDataSet(SmsHistory smsHistory) {
        return String.format("{\n" +
                        "    \"message\":\"%s\",\n" +
                        "    \"transaction_id\":[\"%s\"],\n" +
                        "    \"uuid\":\"%s\",\n" +
                        "    \"fragments_count\":%s,\n" +
                        "    \"code\":%s\n" +
                        "}", "OK"
                , app.generate().randomString(9)
                , smsHistory.getUuid()
                , 1
                , 0);
    }

    public String randomSmsSendingInfoMQDataSet2(SmsHistory smsHistory) {

        String json = gson.toJson(smsHistory);
        return json;
    }

    public String randomSmsSendingInfoMQDataSet4(SmsHistory smsHistory) {
        String string = gson.toJson(smsHistory);
        JSONObject json = new JSONObject(string);
        return json.toString();
    }

    public String randomReceivedSmsMQDataSet2(ReceivedSmsMQDataSet receivedSmsMQDataSet) {

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String string = gson.toJson(receivedSmsMQDataSet);
        JSONObject json = new JSONObject(string);
        String newString =  json.toString(4).replaceAll(": ", ":");

        return newString;
    }

    public String randomReceivedSmsMQDataSet3(ReceivedSmsMQDataSet receivedSmsMQDataSet) {
        String jsonmessage = rabbitmqJson.write(receivedSmsMQDataSet);
        jsonmessage = jsonmessage.replaceAll(",(\n)", ", $4");
        return jsonmessage;
    }

    public String randomReceivedSmsMQDataSet5(ReceivedSmsMQDataSet receivedSmsMQDataSet) {

        Gson gson = new GsonBuilder().create();
        String string = gson.toJson(receivedSmsMQDataSet);
        JSONObject json = new JSONObject(string);
        JsonElement el = JsonParser.parseString(string);
        return gson.toJson(el);
    }
}
