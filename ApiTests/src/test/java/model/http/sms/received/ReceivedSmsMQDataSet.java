package model.http.sms.received;

import java.util.List;

public class ReceivedSmsMQDataSet {

    private String source_number;
    private String destination_number;
    private String uuid;
    private String message;
    private int fragments_count;
    private String received_date;

    public String getSourceNumber() {
        return source_number;
    }
    public String getDestinationNumber() {
        return destination_number;
    }
    public String getUuid() {
        return uuid;
    }
    public String getMessage() {
        return message;
    }
    public int getFragmentsCount() {
        return fragments_count;
    }
    public String getReceivedDate() {
        return received_date;
    }

    public ReceivedSmsMQDataSet withSourceNumber(String source_number) {
        this.source_number = source_number;
        return this;
    }
    public ReceivedSmsMQDataSet withDestinationNumber(String destination_number) {
        this.destination_number = destination_number;
        return this;
    }
    public ReceivedSmsMQDataSet withUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
    public ReceivedSmsMQDataSet withMessage(String message) {
        this.message = message;
        return this;
    }
    public ReceivedSmsMQDataSet withFragmentsCount(int fragments_count) {
        this.fragments_count = fragments_count;
        return this;
    }
    public ReceivedSmsMQDataSet withReceivedDate(String received_date) {
        this.received_date = received_date;
        return this;
    }
}
