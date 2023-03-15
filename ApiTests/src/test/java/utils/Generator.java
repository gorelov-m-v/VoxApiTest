package utils;

import java.util.List;
import java.util.Random;

public class Generator {

    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String DIGITS = "0123456789";
    public String[] CURRENCY  = {"RUR", "USD", "EUR", "KZT"};
    public String api_key = "sasa";
    public String password = "Aa123456!";
    Random random = new Random();

    public String randomString(int length) {
        String combination = LOWER + DIGITS + UPPER;
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

    public String randomAccountName() {
        String prefix = "test-";
        return prefix + randomString(20 - prefix.length());
    }

    public int randomValue(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
