package com.mooc.house.hsrv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/416:06
 */
@RequestMapping("redis")
@RestController
public class BaseRedisController {
    private static final Logger log = LoggerFactory.getLogger(BaseRedisController.class);
    @Resource
    private RedisTemplate redisTemplate;
    @RequestMapping("baseRedis")
    public void baseRedis(){
        //设置一个key-val
        redisTemplate.opsForValue().set("redistemplateset","1");
        //设置一个带过期时间
        redisTemplate.opsForValue().set("redistemplateset001","001",10, TimeUnit.SECONDS);
        //设置一个偏移量
        redisTemplate.opsForValue().set("redistemplateset002","hello world");
        redisTemplate.opsForValue().set("redistemplateset002","redis", 6);
        System.out.println("***************"+redisTemplate.opsForValue().get("redistemplateset002"));

        System.out.println(redisTemplate.opsForValue().setIfAbsent("redistemplateset003","multi1"));//false  multi1之前已经存在
        System.out.println(redisTemplate.opsForValue().setIfAbsent("redistemplateset003","multi111"));//true  multi111之前不存在

    }
    public void getRedis(){
        Object redistemplateset = redisTemplate.opsForValue().get("redistemplateset");
    }
}
