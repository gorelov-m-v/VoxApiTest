package response.accounts;

import response.accounts.paths.Errors;
import response.accounts.paths.Error;

import java.util.List;

// VoxDocs  https://voximplant.com/docs/references/httpapi/accounts#addaccount
public class AddAccountResponse {
    private int result;
    private int account_id;
    private String api_key;
    private int billing_account_id;
    private boolean active;
    private Error error;
    private List<Errors> errors;

    public int result() {
        return result;
    }
    public int account_id() {
        return account_id;
    }
    public String api_key() {
        return api_key;
    }
    public int billing_account_id() {
        return billing_account_id;
    }
    public boolean active() {
        return active;
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
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
    public void setBilling_account_id(int billing_account_id) {
        this.billing_account_id = billing_account_id;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setError(Error error) {
        this.error = error;
    }
    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }
}
