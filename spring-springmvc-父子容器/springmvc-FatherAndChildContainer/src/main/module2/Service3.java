package main.module2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service3 {

    //module1中的service2
    @Autowired
    private main.module1.Service2 service2;

    //module2中的service1
    @Autowired
    private main.module2.Service1 service1;


    //module1中的service2   service1的方法
    public String m1() {
        return this.service2.m1();
    }

    //module2中的service1  service1方法
    public String m2() {
        return this.service1.m2();
    }
}
