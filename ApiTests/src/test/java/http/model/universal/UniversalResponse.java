package http.model.universal;

import java.util.List;

// https://voximplant.com/docs/references/httpapi/sms#controlsms
public class UniversalResponse {

    private int result;
    private Error error;
    private List<Errors> errors;

    public int getResult() {
        return result;
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
    public void setError(Error error) {
        this.error = error;
    }
    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }
}
