package com.demo.boot.api.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by WuHaiming on 2017/5/11 0011.
 * md5加密工具类
 */
public class MD5Util {

    /**
     * 32位MD5加密方法
     * 16位小写加密只需getMd5Value("xxx").substring(8, 24);即可
     *
     * @param context
     * @return
     */
    public static String encrypt(String context) {
        StringBuilder buf = new StringBuilder("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes(StandardCharsets.UTF_8));//update处理
            byte [] encryContext = md.digest();//调用该方法完成计算

            int i;
            for (byte b : encryContext) {//做相应的转化（十六进制）
                i = b;
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buf.toString();
    }

}
