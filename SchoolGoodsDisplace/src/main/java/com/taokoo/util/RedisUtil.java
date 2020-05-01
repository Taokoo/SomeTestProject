package com.taokoo.util;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis缓存工具类
 * 
 * @author Taokoo
 * @date 2020/05/01
 */
@Component
public class RedisUtil {
    
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 存入redis缓存
     * @param key
     * @param value
     * @param timeout 过期时间 ；单位：秒
     */
    public void setRedis(String key,String value,int timeout) {
        redisTemplate.opsForValue().set(key, value,timeout,TimeUnit.SECONDS);
        System.out.println(key+"存入redis缓存成功"+"，值为:"+value+"，过期时间："+timeout+"秒。");
    }

    /**
     * 从redis中取出数据
     * @param key
     * @return value
     */
    public String getRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 从redis中删除值
     * @param key
     */
    public void delRedis(String key) {
        Boolean isDel = redisTemplate.delete(key);
        System.out.println(key+"删除结果:" + isDel);
    }
}
