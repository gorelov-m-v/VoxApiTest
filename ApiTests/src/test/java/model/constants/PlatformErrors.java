package model.constants;

public enum PlatformErrors {

    ERROR_113(113, "Account name must be at least 5 and up to 20 characters long.", "account_name"),
    ERROR_464(464, "Password should be at least 8 characters long and contain uppercase characters (A-Z)," +
            " lowercase" +" characters (a-z), digits (0-9) and special characters (~!@#$%^&*_-+=`|\\(){}[]:;\"'<>,.?/)"
            , "account_password"),
    ERROR_210(210, "The 'currency' parameter is invalid.", "currency");

    private int code;
    private String msg;
    private String field_name;

    PlatformErrors(int code, String msg, String field_name) {
        this.code = code;
        this.msg = msg;
        this.field_name = field_name;
    }

    public static PlatformErrors getError(int code) {
        return PlatformErrors.valueOf("ERROR_" + code);
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public String getField_name() {
        return field_name;
    }
}
