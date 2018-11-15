package com.jacknolfskin.htool.rsa.validate.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Map;

/**
 * @Auther: ifly
 * @Date: 2018/11/13 15:59
 * @Description:
 */
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    }

    /**
     * 将对象序列化为json byte数组
     * @param message
     * @return
     */
    public static byte[] encodeByte(Object message){
        try {
            return mapper.writeValueAsBytes(message);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    /**
     * 将对象序列化为json 字符串
     * @param message
     * @return
     */
    public static String encodeString(Object message){
        try {
            return mapper.writeValueAsString(message);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    /**
     * 反序列化一个对象
     * @param message 字符串byte数组
     * @param classOfT 对象类
     * @param charset 字符串字符集
     * @param <T>
     * @return
     */
    public static <T> T decode(byte[] message, Class<T> classOfT, String charset) {
        try{
            String jsonSrc = new String(message, charset);
            return mapper.readValue(jsonSrc, classOfT);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    /**
     * 反序列化一个对象
     * @param message 字符串byte数组
     * @param classOfT 对象类。
     *                 其中classOfT是需要反序列化的对象, 该方式主要解决集合类的反序列化问题
     * @param charset
     * @param <T>
     * @return
     */
    public static <T> T decode(byte[] message, TypeReference classOfT, String charset) {
        try{

            String jsonSrc = new String(message, charset);
            return mapper.readValue(jsonSrc, classOfT);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    /**
     * 默认以utf8编码进行处理
     * @param message
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T decode(byte[] message, Class<T> classOfT) {
        try{
            return mapper.readValue(message, classOfT);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public static <T> T decode(String message, Class<T> classOfT)  {
        try{
            return mapper.readValue(message, classOfT);
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public static Map<String, String> decode2MapString(String message)  {
        try{
            return mapper.readValue(message, new TypeReference<Map<String, String>>(){});
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public static Map<String, Object> decode2MapObject(String message)  {
        try{
            return mapper.readValue(message, new TypeReference<Map<String, Object>>(){});
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
