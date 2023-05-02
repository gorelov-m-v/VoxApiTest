package http.model.sms.received;

public class ReceiverSmsHTTPDataSet {

    // Mandatory
    private String src_number;
    private String dst_number;
    private String content;

    public String getsrc_number() {
        return src_number;
    }
    public String getdst_number() {
        return dst_number;
    }
    public String getContent() {
        return content;
    }

    public ReceiverSmsHTTPDataSet withSrcNumber(String src_number) {
        this.src_number = src_number;
        return this;
    }
    public ReceiverSmsHTTPDataSet withDstNumber(String dst_number) {
        this.dst_number = dst_number;
        return this;
    }
    public ReceiverSmsHTTPDataSet withContent(String content) {
        this.content = content;
        return this;
    }
}
