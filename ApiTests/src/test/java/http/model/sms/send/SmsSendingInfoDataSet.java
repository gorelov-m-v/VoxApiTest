package http.model.sms.send;

import java.util.List;

public class SmsSendingInfoDataSet {
    private String message;
    private String transaction_id;
    private String uuid;
    private int fragments_count;
    private int code;

    public String getMessage() {
        return message;
    }
    public String getTransactionId() {
        return transaction_id;
    }
    public String getUuid() {
        return uuid;
    }
    public int getFragmentsCount() {
        return fragments_count;
    }
    public int getCode() {
        return code;
    }

    public SmsSendingInfoDataSet withMessage(String message) {
        this.message = message;
        return this;
    }
    public SmsSendingInfoDataSet withTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
        return this;
    }
    public SmsSendingInfoDataSet withUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
    public SmsSendingInfoDataSet withFragments_count(int fragments_count) {
        this.fragments_count = fragments_count;
        return this;
    }
    public SmsSendingInfoDataSet withCode(int code) {
        this.code = code;
        return this;
    }
}
