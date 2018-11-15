package com.jacknolfskin.htool.rsa.validate.advice;

import com.jacknolfskin.htool.rsa.validate.util.Base64Utils;
import com.jacknolfskin.htool.rsa.validate.util.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: feihu5
 * @Date: 2018/11/15 09:41
 * @Description:
 */
@Slf4j
public class DecryptHttpInputMessage implements HttpInputMessage {
    private HttpHeaders headers;
    private InputStream body;

    public DecryptHttpInputMessage(HttpInputMessage inputMessage, String privateKey, String charset) throws Exception {

        if (StringUtils.isEmpty(privateKey)) {
            throw new IllegalArgumentException("privateKey is null");
        }

        //获取请求内容
        this.headers = inputMessage.getHeaders();
        String content = IOUtils.toString(inputMessage.getBody(), charset);

        String decryptBody;
        if (content.startsWith("{")) {
            log.info("未加密数据不进行解密操作:{}", content);
            decryptBody = content;
        } else {
            StringBuilder json = new StringBuilder();
            content = content.replaceAll(" ", "+");

            if (!StringUtils.isEmpty(content)) {
                String[] contents = content.split("\\|");
                for (String value : contents) {
                    value = new String(RSAUtils.decryptByPrivateKey(Base64Utils.decode(value), privateKey), charset);
                    json.append(value);
                }
            }
            decryptBody = json.toString();
            log.info("接收到加密数据：{},解密后：{}", content, decryptBody);
        }
        this.body = IOUtils.toInputStream(decryptBody, charset);
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
