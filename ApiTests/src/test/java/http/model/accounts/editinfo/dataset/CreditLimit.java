package http.model.accounts.editinfo.dataset;

public class CreditLimit {
    private String value;

    public String getValue() {
        return value;
    }

    public CreditLimit withValue(String value) {
        this.value = value;
        return this;
    }
}
