package org.ywk.common.result;

public enum CommonResultCode implements ResultCode {
    SUCCESS(0,"success"),

    FAIL(-1,"fail"),
    ;


    private final Integer code;

    private final String message;

    CommonResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
