package com.example.demo.controller;

import com.example.demo.entity.UserLoginReq;
import com.example.demo.entity.UserRegisterReq;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping(value = "/register")
    public void exceptionLoginDemo(@Validated UserRegisterReq userRegisterReq) {
        System.out.println("user register!");
    }

    @PostMapping(value = "/login")
    public void exceptionRegisterDemo(@Validated UserLoginReq userLoginReq) {
        System.out.println("user login!");

    }
}
