package model;


public class User {

    private String account_name;
    private String account_password;

    private String account_email;

    private String currency;
    private boolean active;
    private boolean is_trial ;
    private int init_balance;
    private String api_key;


    public String accountName() {
        return account_name;
    }
    public String getAccount_password() {
        return account_password;
    }
    public String getAccount_email() {
        return account_email;
    }
    public String getCurrency() {
        return currency;
    }
    public boolean active() {
        return active;
    }
    public boolean is_trial() {
        return is_trial;
    }
    public int getInitBalance() {
        return init_balance;
    }
    public String getApiKey() {
        return api_key;
    }


    public User withName(String account_name) {
        this.account_name = account_name;
        return this;
    }

    public User withPassword(String account_password) {
        this.account_password = account_password;
        return this;
    }

    public User withEmail(String account_email) {
        this.account_email = account_email;
        return this;
    }

    public User withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public User withActive(boolean active) {
        this.active = active;
        return this;
    }

    public User withIsTrial(boolean is_trial) {
        this.is_trial = is_trial;
        return this;
    }

    public User withInitBalance(int init_balance) {
        this.init_balance = init_balance;
        return this;
    }

    public User withApiKey(String api_key) {
        this.api_key = api_key;
        return this;
    }
}
