package com.jacknolfskin.htool.rsa.validate.advice;

import com.jacknolfskin.htool.rsa.validate.annotation.Encrypt;
import com.jacknolfskin.htool.rsa.validate.auto.SecurityProperties;
import com.jacknolfskin.htool.rsa.validate.util.Base64Utils;
import com.jacknolfskin.htool.rsa.validate.util.JsonUtil;
import com.jacknolfskin.htool.rsa.validate.util.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author: feihu5
 * @Description: 请求响应处理类 对加了@Encrypt的方法的数据进行加密操作
 * @Date: Created in 14:23 2018/11/15
 */
@ControllerAdvice
@Slf4j
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	private boolean encrypt;

	@Autowired
	private SecurityProperties securityProperties;

	private static ThreadLocal<Boolean> encryptLocal = new ThreadLocal<>();

	public static void setEncryptStatus(boolean status) {
		encryptLocal.set(status);
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		encrypt = false;
		if (returnType.getMethod().isAnnotationPresent(Encrypt.class) && !securityProperties.isDebug()) {
			encrypt = true;
		}
		return encrypt;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		//通过调用EncryptResponseBodyAdvice.setEncryptStatus(false);来动态设置不加密操作
		Boolean status = encryptLocal.get();
		if (null != status && !status) {
			encryptLocal.remove();
			return body;
		}
		if (encrypt) {
			String publicKey = securityProperties.getPublicKey();
			try {
				String content = JsonUtil.encodeString(body);
				if (!StringUtils.hasText(publicKey)) {
					throw new NullPointerException("请配置spring.encrypt.privatekeyc参数");
				}
				byte[] data = content.getBytes();
				byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
				String result = Base64Utils.encode(encodedData);
				log.info("发送加密前数据：{}，加密后：{}", content, result);
				return result;
			} catch (Exception e) {
				log.error("加密数据异常", e);
			}
		}
		return body;
	}
}
