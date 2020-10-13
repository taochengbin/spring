package com.example.demo.exception;

import cn.hutool.json.JSONObject;

/**
 * 注册异常
 */
public class ChildRegisterException extends FatherException {

    private static final long serialVersionUID = 816726270176940141L;

    private static final String EXCEPTION_TYPE = "REGISTER";

    public ChildRegisterException(ExceptionEnum exceptionEnum, JSONObject params) {
        super(exceptionEnum, EXCEPTION_TYPE, params);
    }

    public ChildRegisterException(ExceptionEnum exceptionEnum, JSONObject params, Throwable cause) {
        super(exceptionEnum, EXCEPTION_TYPE, params, cause);
    }
}
