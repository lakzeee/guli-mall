package com.glm.common.exception;

public enum ExceptionCodeEnum {
    UNKNOWN_EXCEOTION(10000, "Unknown Error"),
    VALID_EXCEPTION(10001, "Data Validation Error"),

    PRODUCT_UP_EXCEPTION(11000, "Change product status to up error");
    private Integer code;
    private String msg;
    ExceptionCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
