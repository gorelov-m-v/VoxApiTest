package model.http.phonenumbers.attach;

//https://voximplant.com/docs/references/httpapi/phonenumbers#bindphonenumbertoapplication
public class AttachPhoneNumberDataSet {

    // Mandatory
    private int account_id;
    private String  api_key;
    private String country_code;
    private String phone_category_name;
    private String phone_region_id;

    //Optional
    private String phone_number;
    private String country_state;
    private Integer regulation_address_id;

    public int getAccountId() {
        return account_id;
    }
    public String getApiKey() {
        return api_key;
    }
    public String getCountryCode() {
        return country_code;
    }
    public String getPhoneCategoryName() {
        return phone_category_name;
    }
    public String getPhoneRegionId() {
        return phone_region_id;
    }

    public String getPhoneNumber() {
        return phone_number;
    }
    public String getCountryState() {
        return country_state;
    }
    public Integer getRegulationAddressId() {
        return regulation_address_id;
    }

    public AttachPhoneNumberDataSet withAccountId(int account_id) {
        this.account_id = account_id;
        return this;
    }
    public AttachPhoneNumberDataSet withApiKey(String api_key) {
        this.api_key = api_key;
        return this;
    }
    public AttachPhoneNumberDataSet withCountryCode(String country_code) {
        this.country_code = country_code;
        return this;
    }
    public AttachPhoneNumberDataSet withPhoneCategoryName(String phone_category_name) {
        this.phone_category_name = phone_category_name;
        return this;
    }
    public AttachPhoneNumberDataSet withPhoneRegionId(String phone_region_id) {
        this.phone_region_id = phone_region_id;
        return this;
    }

    public AttachPhoneNumberDataSet withPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }
    public AttachPhoneNumberDataSet withCountryState(String country_state) {
        this.country_state = country_state;
        return this;
    }
    public AttachPhoneNumberDataSet withRegulationAddressId(Integer regulation_address_id) {
        this.regulation_address_id = regulation_address_id;
        return this;
    }
}
