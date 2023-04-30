package http.model.accounts.setdocument;

import http.model.universal.Error;
import http.model.universal.Errors;
import java.util.List;

public class SetAccountDocumentResponse {
    private int result;
    private String document_status;
    private int document_id;
    private Error error;
    private List<Errors> errors;

    public int getResult() {
        return result;
    }
    public String getDocument_status() {
        return document_status;
    }
    public int getDocument_id() {
        return document_id;
    }
    public Error getError() {
        return error;
    }
    public List<Errors> getErrors() {
        return errors;
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
    public void setError(Error error) {
        this.error = error;
    }
    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }
}
