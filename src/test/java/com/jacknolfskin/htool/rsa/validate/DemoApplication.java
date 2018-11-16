package com.jacknolfskin.htool.rsa.validate;

import com.jacknolfskin.htool.rsa.validate.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableSecurity
@ComponentScan(basePackages = {"com.jacknolfskin.htool"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
