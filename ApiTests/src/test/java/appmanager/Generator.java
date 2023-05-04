package appmanager;

import http.model.sms.received.ReceiverSmsHTTPDataSet;
import http.model.sms.send.SendSmsMessageDataSet;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import http.model.accounts.add.AddAccountResponse;
import http.model.accounts.add.AddAccountDataSet;
import http.model.accounts.setdocument.AccountDocumentDataSet;
import http.model.phonenumbers.attach.AttachPhoneNumberDataSet;
import http.model.phonenumbers.attach.AttachPhoneNumberResponse;
import http.model.sms.control.ControlSmsDataSet;
import http.model.sms.received.ReceivedSmsMQDataSet;
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

    public AddAccountDataSet randomUser() {
        return new AddAccountDataSet().withEmail(app.generate().randomEmail())
                .withName(app.generate().randomAccountName(15))
                .withPassword(app.generate().simplePassword)
                .withApiKey(app.getProperty("papi.admin_api-key"))
                .withActive(true);
    }

    public AccountDocumentDataSet randomAccountDocument(AddAccountResponse addAccountResponse) {
        return new AccountDocumentDataSet().withAccountId(addAccountResponse.account_id())
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

    public AttachPhoneNumberDataSet randomAttachPhoneNumber(AddAccountResponse addAccountResponse) {
        return new AttachPhoneNumberDataSet().withAccountId(addAccountResponse.account_id())
                                      .withApiKey(addAccountResponse.api_key())
                                      .withCountryCode("BE")
                                      .withPhoneRegionId("20560")
                                      .withPhoneCategoryName("MOBILE");
    }

    public ControlSmsDataSet randomControlSmsDataSet(AddAccountResponse addAccountResponse, AttachPhoneNumberResponse attachPhoneNumberResponse) {
        return new ControlSmsDataSet().withAccountId(addAccountResponse.account_id())
                .withApiKey(addAccountResponse.api_key())
                .withPhoneNumber(attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withCommand("enable");
    }
    public ReceivedSmsMQDataSet randomReceivedSmsMQDataSet(AttachPhoneNumberResponse attachPhoneNumberResponse) {
        return new ReceivedSmsMQDataSet().withSourceNumber(app.generate().randomString(8))
                .withDestinationNumber(attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withUuid(UUID.randomUUID().toString())
                .withFragmentsCount(1)
                .withReceivedDate(LocalDate.now())
                .withMessage(app.generate().randomString(20));
    }

    public String randomReceivedSmsMQDataSet(AttachPhoneNumberResponse attachPhoneNumberResponse, ReceivedSmsMQDataSet receivedSmsMQDataSet) {

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

    public ReceiverSmsHTTPDataSet randomReceivedSmsHTTPDataSet(AttachPhoneNumberResponse attachPhoneNumberResponse) {
        return new ReceiverSmsHTTPDataSet().withSrcNumber(app.generate().randomString(8))
                .withDstNumber(attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withContent(app.generate().randomString(20));
    }

    public SendSmsMessageDataSet randomSendSmsMessageDataSet(AddAccountResponse addAccountResponse,
                                                             AttachPhoneNumberResponse attachPhoneNumberResponse) {
        return new SendSmsMessageDataSet()
                .withAccountId(addAccountResponse.account_id())
                .withApiKey(addAccountResponse.api_key())
                .withSource(attachPhoneNumberResponse.getPhone_numbers().get(0).getPhone_number())
                .withDestination("32466902107")
                .withSmsBody(app.generate().randomString(20))
                .withStoreBody(true);
    }
}
