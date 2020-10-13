package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserLoginReq {
    @NotEmpty(message = "用户姓名不能为空")
    @Size(max = 64, message = "用户姓名长度不能超过64位")
    private String name;

    @NotEmpty(message = "密码不能为空")
    private String password;

}
