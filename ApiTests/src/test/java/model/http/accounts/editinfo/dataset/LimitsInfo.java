package model.http.accounts.editinfo.dataset;

public class LimitsInfo {
    private String accountId;
    private FinancialLimits financialLimits;

    public String getAccountId() {
        return accountId;
    }
    public FinancialLimits getFinancialLimits() {
        return financialLimits;
    }

    public LimitsInfo withAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }
    public LimitsInfo withFinancialLimits(FinancialLimits financialLimits) {
        this.financialLimits = financialLimits;
        return this;
    }
}
