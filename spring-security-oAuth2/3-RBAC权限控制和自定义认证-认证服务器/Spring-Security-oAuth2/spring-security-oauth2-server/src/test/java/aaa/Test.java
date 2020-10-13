package aaa;

import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Test {
    @org.junit.Test
    public void ecode(){

        System.out.println(new BCryptPasswordEncoder().encode("admin888"));
        System.out.println("secret:#####" + new BCryptPasswordEncoder().encode("secret"));
    }
}
