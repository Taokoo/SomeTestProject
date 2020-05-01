package com.taokoo.util;

import java.util.UUID;

/**
 * 随机验证码工具类
 * 
 * @author Taokoo
 * @date 2020/05/01
 */
public class ValiCodeUtil {
    
    /**
     * 随机生成6位数验证码
     * @return
     */
    public static String getRandom() {
        String num = "";
        for (int i = 0; i < 6; i++) {
            num = num + String.valueOf((int)Math.floor(Math.random() * 9 + 1));
        }
        return num;
    }
    
    /**
     * 生成UUID
     * @param args
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
