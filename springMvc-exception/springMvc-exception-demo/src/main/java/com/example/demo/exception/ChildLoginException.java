package com.example.demo.exception;

import cn.hutool.json.JSONObject;

/**
 * 登录异常
 */
public class ChildLoginException extends FatherException {

    private static final long serialVersionUID = -7065580338200227702L;

    private static final String EXCEPTION_TYPE = "LOGIN";

    public ChildLoginException(ExceptionEnum exceptionEnum, JSONObject params) {
        super(exceptionEnum, EXCEPTION_TYPE, params);
    }

    public ChildLoginException(ExceptionEnum exceptionEnum, JSONObject params, Throwable cause) {
        super(exceptionEnum, EXCEPTION_TYPE, params, cause);
    }
}
