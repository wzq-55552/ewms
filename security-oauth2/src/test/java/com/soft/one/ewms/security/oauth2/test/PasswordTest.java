package com.soft.one.ewms.security.oauth2.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PasswordTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void test(){
        //String encode = passwordEncoder.encode("123456");
        String encode = passwordEncoder.encode("ewms");
        System.out.println(encode);
    }

    @Test
    public void testBCryptPasswordEncoder() {
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }
}


