package model.http.sms.send;

// https://voximplant.com/docs/references/httpapi/sms#sendsmsmessage
public class SendSmsMessageResponse {
    private int result;
    private int message_id;
    private int fragments_count;

    public int getResult() {
        return result;
    }
    public int getMessageId() {
        return message_id;
    }
    public int getFragmentsCount() {
        return fragments_count;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }
    public void setFragments_count(int fragments_count) {
        this.fragments_count = fragments_count;
    }
}
