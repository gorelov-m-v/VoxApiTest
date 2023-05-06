package model.http.accounts.editinfo.dataset;

public class FinancialLimits {
    private CreditLimit creditLimit;

    public CreditLimit getCreditLimit() {
        return creditLimit;
    }

    public FinancialLimits withCreditLimit(CreditLimit creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }
}
