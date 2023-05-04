package constants;

import http.model.accounts.add.AddAccountResponse;

public class Paths {

    // Papi
    public String addAccount = "/platform_api/AddAccount";
    public String setAccountDocument = "/platform_api/SetAccountDocument";
    public String attachPhoneNumber = "/platform_api/AttachPhoneNumber";
    public String controlSms = "/platform_api/ControlSms";


    // Sms_gw
    public String receivedSms = "/sms.runexis";

    // BO envoy
    public String getEditAccountInfoPath(AddAccountResponse addAccountResponse) {
        return String.format("/v1/accounts/%s/edit", addAccountResponse.account_id());
    }
}
