package com.jacknolfskin.htool.rsa.validate.controller;

import com.jacknolfskin.htool.rsa.validate.annotation.Decrypt;
import com.jacknolfskin.htool.rsa.validate.annotation.Encrypt;
import com.jacknolfskin.htool.rsa.validate.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: feihu5
 * @Date: 2018/11/9 15:08
 * @Description:
 */
@RestController
@Slf4j
public class EncryptController {

    @Encrypt
    @GetMapping("getUser")
    public User getUser() {
        User user = new User();
        user.setName("jacknolfskin");
        user.setId("1dgd12");
        user.setPassword("12fad6");
        log.info("用户数据：{}", user);
        return user;
    }


    @Decrypt
    @PostMapping("postUser")
    public void postUser(@RequestBody User user) {
        log.info("解密后User: {}", user.toString());
    }
}
