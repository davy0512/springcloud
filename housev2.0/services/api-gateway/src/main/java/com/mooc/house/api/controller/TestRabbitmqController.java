package com.mooc.house.api.controller;

import com.mooc.house.api.rabbirmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/815:09
 */
@RestController
@RequestMapping("testmq")
public class TestRabbitmqController {
    @Autowired
    MQSender mqSender;

    @RequestMapping("/testMQsender")
    public void testMQsender(){
        mqSender.send("HELLO RABBITMQ");
    }

    @RequestMapping("/testTopicMQsender")
    public void testTopicMQsender(){
        mqSender.sendTopic1("topic.queue1");
        mqSender.sendTopic2("topic.queue2");
    }

//    @RequestMapping("/testFanoutMQsender")
//    public void testFanout(){
//        mqSender.sendFanout("fanoutxchage");
//    }
    @RequestMapping("/testFanoutMQsender")
    public void testFanout(){
        mqSender.sendHeader("fanoutxchage");
    }




}
