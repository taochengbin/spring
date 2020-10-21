package com.demo.action;

import com.opensymphony.xwork2.ActionSupport;
import com.demo.model.UserEntity;
import com.demo.service.UserService;

public class TestAction extends ActionSupport {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String test(){
        UserEntity userEntity = userService.getUser(1);
        System.out.println(userEntity);
        return "test";
    }


}
