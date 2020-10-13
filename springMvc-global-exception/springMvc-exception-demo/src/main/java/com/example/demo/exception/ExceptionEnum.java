package com.example.demo.exception;

public enum ExceptionEnum {

    // 请求异常集
    LOGIN_PARAM_EXCEPTION("100101", "登录请求参数错误异常"),
    REGISTER_PARAM_EXCEPTION("100102", "注册请求参数错误异常",true),

    //系统未知异常
    UNKNOWN_EXCEPTION("990101", "未知异常", true);
    private String code;

    private String message;

    private boolean isWarn;


    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
        this.isWarn = false;
    }

    ExceptionEnum(String code, String message, boolean isWarn) {
        this.code = code;
        this.message = message;
        this.isWarn = isWarn;
    }


    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isWarn() {
        return this.isWarn;
    }


}
