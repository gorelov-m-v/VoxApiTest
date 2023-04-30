package http.model.sms.control;

// https://voximplant.com/docs/references/httpapi/sms#controlsms
public class ControlSmsDataSet {

    // Mandatory
    private int account_id;
    private String api_key;
    private String phone_number;
    private String command;

    public int getAccountId() {
        return account_id;
    }
    public String getApiKey() {
        return api_key;
    }
    public String getPhoneNumber() {
        return phone_number;
    }
    public String getCommand() {
        return command;
    }

    public ControlSmsDataSet withAccountId(int account_id) {
        this.account_id = account_id;
        return this;
    }
    public ControlSmsDataSet withApiKey(String api_key) {
        this.api_key = api_key;
        return this;
    }
    public ControlSmsDataSet withPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
        return this;
    }
    public ControlSmsDataSet withCommand(String command) {
        this.command = command;
        return this;
    }
}
