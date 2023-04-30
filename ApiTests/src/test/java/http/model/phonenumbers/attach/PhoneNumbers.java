package http.model.phonenumbers.attach;

public class PhoneNumbers {

    private int subscription_id;
    private String phone_number;
    private String verification_status;
    private int phone_id;

    public int getSubscription_id() {
        return subscription_id;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public String getVerification_status() {
        return verification_status;
    }
    public int getPhone_id() {
        return phone_id;
    }

    public void setSubscription_id(int subscription_id) {
        this.subscription_id = subscription_id;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public void setVerification_status(String verification_status) {
        this.verification_status = verification_status;
    }
    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }
}
