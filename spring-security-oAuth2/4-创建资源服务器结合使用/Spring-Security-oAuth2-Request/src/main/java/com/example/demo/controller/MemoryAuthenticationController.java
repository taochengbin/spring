package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoryAuthenticationController {

    @RequestMapping("/testMemoryRequest")
    public void test(){

        System.out.println("aaaa");
    }
}
