package com.jacknolfskin.htool.rsa.validate.annotation;

import java.lang.annotation.*;

/**
 * 解密注解
 * 加了此注解的接口将进行数据解密操作
 * @author: feihu5
 * @Date: Created in 14:23 2018/11/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

}
