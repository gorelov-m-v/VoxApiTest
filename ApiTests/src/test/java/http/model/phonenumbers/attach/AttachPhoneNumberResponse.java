package http.model.phonenumbers.attach;

import java.util.List;

//https://voximplant.com/docs/references/httpapi/phonenumbers#bindphonenumbertoapplication
public class AttachPhoneNumberResponse {

    private int result;
    private List<PhoneNumbers> phone_numbers;

    public int getResult() {
        return result;
    }
    public List<PhoneNumbers> getPhone_numbers() {
        return phone_numbers;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public void setPhone_numbers(List<PhoneNumbers> phone_numbers) {
        this.phone_numbers = phone_numbers;
    }
}
