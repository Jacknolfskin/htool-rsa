package com.jacknolfskin.htool.rsa.validate.annotation;

import java.lang.annotation.*;

/**
 * 加密注解
 * 
 * 加了此注解的接口将进行数据加密操作
 * @author: feihu5
 * @Date: Created in 14:23 2018/11/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

}
