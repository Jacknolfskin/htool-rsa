package com.jacknolfskin.htool.rsa.validate.auto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: feihu5
 * @Description: 读取RSA相关配置数据
 * @Date: Created in 2018/11/15
 */
@Data
@ConfigurationProperties(prefix = "spring.encrypt")
@Configuration
public class SecurityProperties {
    /**
     * RSA解密私钥
     */
    private String privateKey;
    /**
     * RSA加密公钥
     */
    private String publicKey;
    /**
     * 字符集
     */
    private String charset = "UTF-8";
    /**
     * 开启调试模式，调试模式下不进行加解密操作，用于像Swagger这种在线API测试场景
     */
    private boolean debug = false;
}
