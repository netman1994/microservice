package org.ywk.common.result;

public class ResultResponse<T> {

    private int code;

    private String message;

    private T obj;

    private long timestamp;

    public ResultResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.obj = data;
        this.timestamp = System.currentTimeMillis();
    }

    private ResultResponse(ResultCode resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMessage(), data);
    }



    public static <T> ResultResponse<T> success() {
        return new ResultResponse<>(CommonResultCode.SUCCESS,null);
    }

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(CommonResultCode.SUCCESS,data);
    }


    public static <T> ResultResponse<T> fail(T data) {
        return new ResultResponse<>(CommonResultCode.FAIL,data);
    }

    public static <T> ResultResponse<T> fail() {
        return new ResultResponse<>(CommonResultCode.FAIL,null);
    }

    public static <T> ResultResponse<T> fail(ResultCode resultCode) {
        return new ResultResponse<>(resultCode,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", obj=" + obj +
                ", timestamp=" + timestamp +
                '}';
    }
}
