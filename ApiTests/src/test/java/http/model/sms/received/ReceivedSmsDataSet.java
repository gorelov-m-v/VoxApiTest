package http.model.sms.received;

import java.time.LocalDate;
import java.util.List;

public class ReceivedSmsDataSet {

    private String source_number;
    private String destination_number;
    private List<String> uuid;
    private String message;
    private int fragments_count;
    private LocalDate received_date;

    public String getSourceNumber() {
        return source_number;
    }
    public String getDestinationNumber() {
        return destination_number;
    }
    public List<String> getUuid() {
        return uuid;
    }
    public String getMessage() {
        return message;
    }
    public int getFragmentsCount() {
        return fragments_count;
    }
    public LocalDate getReceivedDate() {
        return received_date;
    }

    public ReceivedSmsDataSet withSourceNumber(String source_number) {
        this.source_number = source_number;
        return this;
    }
    public ReceivedSmsDataSet withDestinationNumber(String destination_number) {
        this.destination_number = destination_number;
        return this;
    }
    public ReceivedSmsDataSet withUuid(List<String> uuid) {
        this.uuid = uuid;
        return this;
    }
    public ReceivedSmsDataSet withMessage(String message) {
        this.message = message;
        return this;
    }
    public ReceivedSmsDataSet withFragmentsCount(int fragments_count) {
        this.fragments_count = fragments_count;
        return this;
    }
    public ReceivedSmsDataSet withReceivedDate(LocalDate received_date) {
        this.received_date = received_date;
        return this;
    }
}
