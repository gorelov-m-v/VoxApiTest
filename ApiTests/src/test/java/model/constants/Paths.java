package model.constants;

import model.http.accounts.add.AddAccountResponse;

public enum Paths {

    ADD_ACCOUNT("/platform_api/AddAccount"),
    SET_ACCOUNT_DOCUMENT("/platform_api/SetAccountDocument"),
    ATTACH_PHONE_NUMBER("/platform_api/AttachPhoneNumber"),
    CONTROL_SMS("/platform_api/ControlSms"),
    SEND_SMS_MESSAGE("/platform_api/SendSmsMessage"),
    RECEIVED_SMS("/sms.runexis");

    private String path;
    Paths(String path) {
        this.path = path;
    }

    public static String getEditAccountInfoPath(AddAccountResponse addAccountResponse) {
        return String.format("/v1/accounts/%s/edit", addAccountResponse.account_id());
    }

    @Override
    public String toString() {
        return path;
    }
}

