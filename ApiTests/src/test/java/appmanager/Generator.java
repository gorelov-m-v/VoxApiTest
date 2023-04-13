package appmanager;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Generator extends HelperBase {

    public Generator(ApplicationManager app) {
        super(app);
    }

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "~!@#$%^&*_-+=`|\\(){}[]:;\"'<>,.?/";
    public static final String ALPHANUMERIC_AND_SPECIAL_SYMBOLS = UPPER + LOWER + DIGITS + SPECIAL;

    public String[] CURRENCY  = {"RUR", "USD", "EUR", "KZT"};
    public String api_key = "sasa";
    public String simplePassword = "Aa123456!";
    Random random = new Random();

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

}
