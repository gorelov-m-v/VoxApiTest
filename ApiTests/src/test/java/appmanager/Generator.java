package appmanager;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import requests.model.*;
import response.accounts.AddAccountResponse;
import response.accounts.AttachPhoneNumberResponse;

import java.time.LocalDate;
import java.util.*;

public class Generator extends HelperBase {
    Random random = new Random();

    public Generator(ApplicationManager app) {
        super(app);
    }

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "~!@#$%^&*_-+=`|\\(){}[]:;\"'<>,.?/";
    public static final String ALPHANUMERIC_AND_SPECIAL_SYMBOLS = UPPER + LOWER + DIGITS + SPECIAL;
    public String simplePassword = "Aa123456!";

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

    public static String randomPassword(int length) {
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

    public String randomAccountName(int length) {
        return randomString(length);
    }

    public int randomValue(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public User randomUser() {
        return new User().withEmail(app.generate().randomEmail())
                .withName(app.generate().randomAccountName(15))
                .withPassword(app.generate().simplePassword)
                .withApiKey(app.getProperty("papi.admin_api-key"))
                .withActive(true);
    }

    public AccountDocument randomAccountDocument(AddAccountResponse addAccountResponse) {
        return new AccountDocument().withAccountId(addAccountResponse.account_id())
                .withApiKey(app.getProperty("papi.admin_api-key")).withLegalStatus("individual")
                .withEsiaVerified(true).withIndividualPassportSeries(1222)
                .withIndividualPassportNumber(555667)
                .withIndividualPassportIssuedBy("sdadasd")
                .withIndividualPassportIssueDate(LocalDate.now().toString())
                .withIndividualFullName("asdasdasd").withIndividualBirthDate(LocalDate.now().toString())
                .withIndividualRegistrationAddress("dasdasd")
                .withIndividualPhoneNumber("79032530778")
                .withDocumentDeliveryAddress("sadasdasdasd").withEmail("mail123123dd@mail.ru");
    }

    public AttachPhoneNumber randomAttachPhoneNumber(AddAccountResponse addAccountResponse) {
        return new AttachPhoneNumber().withAccountId(addAccountResponse.account_id())
                                      .withApiKey(addAccountResponse.api_key())
                                      .withCountryCode("BE")
                                      .withPhoneRegionId("20560")
                                      .withPhoneCategoryName("MOBILE");
    }

    public ControlSms randomControlSms(AddAccountResponse addAccountResponse, AttachPhoneNumberResponse attachPhoneNumberResponse) {
        return new ControlSms().withAccountId(addAccountResponse.account_id())
                .withApiKey(addAccountResponse.api_key())
                .withPhoneNumber(attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withCommand("enable");
    }

    public String randomReceivedSmsDataSet(AttachPhoneNumberResponse attachPhoneNumberResponse) {


        String message = String.format("{\n" +
                "    \"source_number\":\"%s\",\n" +
                "    \"destination_number\":\"%s\",\n" +
                "    \"uuid\":[\"%s\"],\n" +
                "    \"message\":\"%s\",\n" +
                "    \"fragments_count\":%s,\n" +
                "    \"received_date\":\"%s\"\n" +
                "}", app.generate().randomString(8)
                   , attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number()
                   , UUID.randomUUID()
                   , app.generate().randomString(15)
                   , 1
                   , LocalDate.now());

        return message;

    }

}
