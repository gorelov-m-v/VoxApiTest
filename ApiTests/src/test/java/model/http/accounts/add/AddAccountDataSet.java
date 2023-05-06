package model.http.accounts.add;

// VoxDocs  https://voximplant.com/docs/references/httpapi/accounts#addaccount
public class AddAccountDataSet {

    // Mandatory
    private String account_name;
    private String account_email;
    private String account_password;
    private boolean active;
    private String api_key;

    // Mandatory for parent account
    private Integer parent_account_id;
    private String parent_account_name;
    private String parent_account_email;
    private String parent_account_api_key;
    private String parent_account_password;

    // Optional
    private String currency;
    private boolean is_trial;
    private Integer init_balance;
    private Double min_balance_to_notify;
    private String account_custom_data;
    private String account_first_name;
    private String account_last_name;
    private boolean account_notifications;
    private boolean tariff_changing_notifications;
    private boolean news_notifications;
    private String language_code;
    private String location;
    private String record_storage_id;
    private String record_storage_name;


    public String accountName() {
        return account_name;
    }
    public String getAccount_password() {
        return account_password;
    }
    public String getAccount_email() {
        return account_email;
    }
    public boolean active() {
        return active;
    }
    public String getApiKey() {
        return api_key;
    }

    public Integer getParent_account_id() {
        return parent_account_id;
    }
    public String getParent_account_name() {
        return parent_account_name;
    }
    public String getParent_account_email() {
        return parent_account_email;
    }
    public String getParent_account_api_key() {
        return parent_account_api_key;
    }
    public String getParent_account_password() {
        return parent_account_password;
    }

    public Double getMin_balance_to_notify() {
        return min_balance_to_notify;
    }
    public String getAccount_custom_data() {
        return account_custom_data;
    }
    public String getAccount_first_name() {
        return account_first_name;
    }
    public String getAccount_last_name() {
        return account_last_name;
    }
    public boolean isAccount_notifications() {
        return account_notifications;
    }
    public boolean isTariff_changing_notifications() {
        return tariff_changing_notifications;
    }
    public boolean isNews_notifications() {
        return news_notifications;
    }
    public String getLanguage_code() {
        return language_code;
    }
    public String getLocation() {
        return location;
    }
    public String getRecord_storage_id() {
        return record_storage_id;
    }
    public String getRecord_storage_name() {
        return record_storage_name;
    }
    public boolean is_trial() {
        return is_trial;
    }
    public String getCurrency() {
        return currency;
    }
    public Integer getInitBalance() {
        return init_balance;
    }


    public AddAccountDataSet withName(String account_name) {
        this.account_name = account_name;
        return this;
    }
    public AddAccountDataSet withPassword(String account_password) {
        this.account_password = account_password;
        return this;
    }
    public AddAccountDataSet withEmail(String account_email) {
        this.account_email = account_email;
        return this;
    }
    public AddAccountDataSet withApiKey(String api_key) {
        this.api_key = api_key;
        return this;
    }
    public AddAccountDataSet withActive(boolean active) {
        this.active = active;
        return this;
    }

    public AddAccountDataSet withParent_account_id(int parent_account_id) {
        this.parent_account_id = parent_account_id;
        return this;
    }
    public AddAccountDataSet withParent_account_name(String parent_account_name) {
        this.parent_account_name = parent_account_name;
        return this;
    }
    public AddAccountDataSet withParent_account_email(String parent_account_email) {
        this.parent_account_email = parent_account_email;
        return this;
    }
    public AddAccountDataSet withParent_account_api_key(String parent_account_api_key) {
        this.parent_account_api_key = parent_account_api_key;
        return this;
    }
    public AddAccountDataSet withParent_account_password(String parent_account_password) {
        this.parent_account_password = parent_account_password;
        return this;
    }

    public AddAccountDataSet withCurrency(String currency) {
        this.currency = currency;
        return this;
    }
    public AddAccountDataSet withIsTrial(boolean is_trial) {
        this.is_trial = is_trial;
        return this;
    }
    public AddAccountDataSet withInitBalance(int init_balance) {
        this.init_balance = init_balance;
        return this;
    }
    public AddAccountDataSet withMin_balance_to_notify(double min_balance_to_notify) {
        this.min_balance_to_notify = min_balance_to_notify;
        return this;
    }
    public AddAccountDataSet withAccount_custom_data(String account_custom_data) {
        this.account_custom_data = account_custom_data;
        return this;
    }
    public AddAccountDataSet withAccount_first_name(String account_first_name) {
        this.account_first_name = account_first_name;
        return this;
    }
    public AddAccountDataSet withAccount_last_name(String account_last_name) {
        this.account_last_name = account_last_name;
        return this;
    }
    public AddAccountDataSet withAccount_notifications(boolean account_notifications) {
        this.account_notifications = account_notifications;
        return this;
    }
    public AddAccountDataSet withTariff_changing_notifications(boolean tariff_changing_notifications) {
        this.tariff_changing_notifications = tariff_changing_notifications;
        return this;
    }
    public AddAccountDataSet withNews_notifications(boolean news_notifications) {
        this.news_notifications = news_notifications;
        return this;
    }
    public AddAccountDataSet withLanguage_code(String language_code) {
        this.language_code = language_code;
        return this;
    }
    public AddAccountDataSet withLocation(String location) {
        this.location = location;
        return this;
    }
    public AddAccountDataSet withRecord_storage_id(String record_storage_id) {
        this.record_storage_id = record_storage_id;
        return this;
    }
    public AddAccountDataSet withRecord_storage_name(String record_storage_name) {
        this.record_storage_name = record_storage_name;
        return this;
    }
}
