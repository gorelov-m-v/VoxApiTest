package response.accounts;

public class SetAccountDocumentResponse {
    private int result;
    private String document_status;
    private int document_id;

    public int getResult() {
        return result;
    }
    public String getDocument_status() {
        return document_status;
    }
    public int getDocument_id() {
        return document_id;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public void setDocument_status(String document_status) {
        this.document_status = document_status;
    }
    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }
}
