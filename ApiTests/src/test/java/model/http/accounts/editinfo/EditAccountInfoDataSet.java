package model.http.accounts.editinfo;

import model.http.accounts.editinfo.dataset.FinancialInfo;
import model.http.accounts.editinfo.dataset.LimitsInfo;

public class EditAccountInfoDataSet {
    private FinancialInfo financialInfo;
    private LimitsInfo limitsInfo;

    public FinancialInfo getFinancialInfo() {
        return financialInfo;
    }
    public LimitsInfo getLimitsInfo() {
        return limitsInfo;
    }

    public EditAccountInfoDataSet withFinancialInfo(FinancialInfo financialInfo) {
        this.financialInfo = financialInfo;
        return this;
    }
    public EditAccountInfoDataSet withLimitsInfo(LimitsInfo limitsInfo) {
        this.limitsInfo = limitsInfo;
        return this;
    }
}

/* Пример JSON
{
  "financialInfo": {
    "isCreditUser": {
      "value": true
    },
    "accountId": "5"
  },
  "limitsInfo": {
    "accountId": "5",
    "financialLimits": {
      "creditLimit": {
        "value": "200"
      }
    }
  }
 */