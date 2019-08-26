package com.springboot.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

//

/**
 * 加密工具类
 */
public class EncryptUtil {

    /**
     * 把字符串通过MD5 来加密 返回加密后的字符串
     */
    public final static String useMD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  apache md5 加密方式
     * @param str 需要加密String
     * @return MD5加密串
     */
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }
}
