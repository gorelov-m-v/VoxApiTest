package model.http.universal.dataset;

public class Errors {

    private String msg;
    private int code;
    private String field_name;

    public String getMsg() {
        return msg;
    }
    public int getCode() {
        return code;
    }
    public String getField_name() {
        return field_name;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

}
