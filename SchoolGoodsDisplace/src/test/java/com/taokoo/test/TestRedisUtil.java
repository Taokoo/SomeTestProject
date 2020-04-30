 package com.taokoo.test;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.taokoo.BaseTest;

public class TestRedisUtil extends BaseTest {
    
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void setRedis() {
        redisTemplate.opsForValue().set("first","测试不过期数据");
        redisTemplate.opsForValue().set("second","测试30秒过期数据",30, TimeUnit.SECONDS);
        System.out.println("存入缓存成功");
    }
    @Test
    public void getRedis(){
        String first = redisTemplate.opsForValue().get("first");
        String second = redisTemplate.opsForValue().get("second");

        System.out.println("取出缓存中first的数据是:"+first);
        System.out.println("取出缓存中second的数据是:"+second);

    }
    @Test
    public void delRedis() {
        //根据key删除缓存
        Boolean first1 = redisTemplate.delete("first");

        System.out.println("是否删除成功:"+first1);
    }
}
