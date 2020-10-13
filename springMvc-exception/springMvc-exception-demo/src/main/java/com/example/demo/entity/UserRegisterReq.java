package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegisterReq {
    @NotEmpty(message = "用户姓名不能为空")
    @Size(max = 64, message = "用户姓名长度不能超过64位")
    private String name;

    @NotEmpty(message = "用户证件类型不能为空")
    @Pattern(regexp = "^(01|02|03|04|99)$", message = "用户证件类型错误")
    private String cardType;

    @NotEmpty(message = "用户证件号码不能为空")
    @Size(max = 32, message = "用户证件号码长度不能超过32位")
    private String cardNo;

    @NotEmpty(message = "用户联系电话不能为空")
    @Size(max = 32, message = "用户联系电话长度不能超过32位")
    private String contactNo;

    @Size(max = 128, message = "联系地址长度不能超过128位")
    private String contactAddress;
}
