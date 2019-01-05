package com.yyfly.common.constant;

/**
 * 响应code枚举
 */
public enum ResponseCode {

    ERROR(-1, "mismatch condition!");


    private final Integer code;

    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
