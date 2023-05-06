package model.http.accounts.editinfo.dataset;

public class FinancialInfo {
    private IsCreditUser isCreditUser;
    private String accountId;

    public IsCreditUser getIsCreditUser() {
        return isCreditUser;
    }
    public String getAccountId() {
        return accountId;
    }

    public FinancialInfo withIsCreditUser(IsCreditUser isCreditUser) {
        this.isCreditUser = isCreditUser;
        return this;
    }
    public FinancialInfo withAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }
}
