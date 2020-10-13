package com.example.demo.exception;

import cn.hutool.json.JSONObject;

/**
 * 父类异常
 *
 * @author JackieGu
 * @date 2020/4/17
 */
public abstract class FatherException extends Exception{

    private static final long serialVersionUID = 2287623661547050627L;

    protected boolean isWarn;

    protected String code;

    protected String message;

    public FatherException(ExceptionEnum exceptionEnum, String type, JSONObject params) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
        this.isWarn = exceptionEnum.isWarn();
    }

    public FatherException(ExceptionEnum exceptionEnum, String type, JSONObject params, Throwable cause) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
        this.isWarn = exceptionEnum.isWarn();
        if (params == null) {
            params = new JSONObject();
        }
        params.put("exception", cause.getMessage());
    }
}

