package com.mooc.house.api.controller;

import com.mooc.house.api.feign.house.houseSerApi;
import com.mooc.house.api.service.TestRedisTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/411:24
 */
@Controller
public class TestRedisTemplateController {
    private static final Logger log = LoggerFactory.getLogger(TestRedisTemplateController.class);

    @Autowired
    private TestRedisTemplate testRedisTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private houseSerApi houseSer;

    @RequestMapping(value="/BaseRedis")
    public void setBaseRedis(){
//        houseSer.baseRedis();
        redisTemplate.opsForValue().set("9895624:","testtetettet");
    }
}
