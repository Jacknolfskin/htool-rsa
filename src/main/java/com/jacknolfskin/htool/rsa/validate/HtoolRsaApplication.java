package com.jacknolfskin.htool.rsa.validate;

import com.jacknolfskin.htool.rsa.validate.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSecurity
public class HtoolRsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HtoolRsaApplication.class, args);
    }
}
