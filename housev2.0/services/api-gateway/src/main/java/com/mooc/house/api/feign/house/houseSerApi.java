package com.mooc.house.api.feign.house;

import com.mooc.house.api.common.RestResponse;
import com.mooc.house.api.model.House;
import com.mooc.house.api.model.ListResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("house")
public interface houseSerApi {
    @RequestMapping(method = RequestMethod.GET, value = "/house/lastest")
    RestResponse<List<House>> getLastest();

    /***
     * 测试缓存(spring data redis)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/redis/baseRedis")
    void baseRedis();

}
