package com.jacknolfskin.htool.rsa.validate.advice;

import com.jacknolfskin.htool.rsa.validate.annotation.Decrypt;
import com.jacknolfskin.htool.rsa.validate.auto.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 请求请求处理类（目前仅仅对requestbody有效）
 * 对加了@Decrypt的方法的数据进行解密密操作
 *
 * @author: feihu5
 * @Description:
 */
@ControllerAdvice
@Slf4j
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {

    private boolean encrypt;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (methodParameter.getMethod().isAnnotationPresent(Decrypt.class) && !securityProperties.isDebug()) {
            encrypt = true;
        }
        return encrypt;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        if (encrypt) {
            try {
                return new DecryptHttpInputMessage(inputMessage, securityProperties.getPrivateKey(), securityProperties.getCharset());
            } catch (Exception e) {
                log.error("数据解密失败", e);
            }
        }
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}


