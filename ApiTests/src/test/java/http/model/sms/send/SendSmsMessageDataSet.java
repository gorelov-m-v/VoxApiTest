package http.model.sms.send;

// https://voximplant.com/docs/references/httpapi/sms#sendsmsmessage
public class SendSmsMessageDataSet {
    private int account_id;
    private String api_key;
    private String destination;
    private String source;
    private String sms_body;
    private boolean store_body;

    public int getAccountId() {
        return account_id;
    }
    public String getApiKey() {
        return api_key;
    }
    public String getDestination() {
        return destination;
    }
    public String getSource() {
        return source;
    }
    public String getSmsBody() {
        return sms_body;
    }
    public boolean isStoreBody() {
        return store_body;
    }

    public SendSmsMessageDataSet withAccountId(int account_id) {
        this.account_id = account_id;
        return this;
    }
    public SendSmsMessageDataSet withApiKey(String api_key) {
        this.api_key = api_key;
        return this;
    }
    public SendSmsMessageDataSet withDestination(String destination) {
        this.destination = destination;
        return this;
    }
    public SendSmsMessageDataSet withSource(String source) {
        this.source = source;
        return this;
    }
    public SendSmsMessageDataSet withSmsBody(String sms_body) {
        this.sms_body = sms_body;
        return this;
    }
    public SendSmsMessageDataSet withStoreBody(boolean store_body) {
        this.store_body = store_body;
        return this;
    }
}
