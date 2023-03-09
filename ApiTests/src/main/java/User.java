public class User {

    private String account_name;
    private String account_password;
    private String account_email;
    private boolean active = true;
    private String currency;
    private int init_balance;
    private boolean is_trial;
    private String language_code;
    private String location;
    private String min_balance_to_notify;
    private String account_first_name;
    private String account_last_name;
    private boolean account_notifications = false;
    private boolean tariff_changing_notifications = false;
    private boolean news_notifications;
    private String record_storage_id;
    private String record_storage_name;

    public User(String account_name, String account_password, String account_email, boolean active, String currency, boolean is_trial) {
        this.account_name = account_name;
        this.account_password = account_password;
        this.account_email = account_email;
        this.active = active;
        this.currency = currency;
        this.is_trial = is_trial;
    }

    public String getAccount_name() {
        return account_name;
    }
    public String getAccount_password() {
        return account_password;
    }
    public String getAccount_email() {
        return account_email;
    }
    public boolean isActive() {
        return active;
    }
    public String getCurrency() {
        return currency;
    }
    public int getInit_balance() {
        return init_balance;
    }
    public boolean isIs_trial() {
        return is_trial;
    }
}
