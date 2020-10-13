package com.example.demo.entity;

import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Result implements Serializable {
    private static final long serialVersionUID = -4127817000970452353L;

    //默认成功正常
    private static final String SUCCESS_CODE = "200";

    //默认成功消息
    private static final String SUCCESS_MESSAGE = "SUCCESS";

    //状态
    private String code;

    //消息
    private String message;

    //数据
    private Object response;

    private Result(Builder builder) {
        code = builder.code == null ? SUCCESS_CODE : builder.code + "";
        message = builder.message == null ? SUCCESS_MESSAGE : builder.message;
        response = builder.response;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer code;
        private String message;
        private Object response;

        private Builder() {
        }

        public Builder code(Integer val) {
            code = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder response(Object val) {
            response = val;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
