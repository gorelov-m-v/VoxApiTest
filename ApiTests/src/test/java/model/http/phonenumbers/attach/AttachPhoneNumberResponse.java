package model.http.phonenumbers.attach;

import model.http.universal.dataset.Error;
import model.http.universal.dataset.Errors;
import java.util.List;

//https://voximplant.com/docs/references/httpapi/phonenumbers#bindphonenumbertoapplication
public class AttachPhoneNumberResponse {

    private int result;
    private List<PhoneNumbers> phone_numbers;
    private Error error;
    private List<Errors> errors;

    public int getResult() {
        return result;
    }
    public List<PhoneNumbers> getPhone_numbers() {
        return phone_numbers;
    }
    public Error getError() {
        return error;
    }
    public List<Errors> getErrors() {
        return errors;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public void setPhone_numbers(List<PhoneNumbers> phone_numbers) {
        this.phone_numbers = phone_numbers;
    }
    public void setError(Error error) {
        this.error = error;
    }
    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }
}
