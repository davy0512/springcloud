package com.mooc.house.api.rabbirmq;

import com.mooc.house.api.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 消息发送者
 * @date 2018/5/814:47
 */
@Service
public class MQSender {
    public static Logger logger =  LoggerFactory.getLogger(MQSender.class);
    @Autowired
    AmqpTemplate amqpTemplate;



    /**
     * Direct 模式   交换机exchangs
     * @param message
     */
    public void send(Object message){
        String msg = message.toString();
        logger.info("send 消息："+msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE,msg);
    }

    /**
     * topic 模式
     * @param message
     */
    public void sendTopic1(Object message){
        String msg = message.toString();
        logger.info("send 消息："+msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_QUEUE1,msg);
    }
    public void sendTopic2(Object message){
        String msg = message.toString();
        logger.info("send 消息："+msg);
        amqpTemplate.convertAndSend(MQConfig.TOPIC_QUEUE2,msg);
    }

//    public void sendFanout(Object message) {
//        String msg = message.toString();
//        logger.info("send fanout message:"+msg);
//        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);
//    }

    public void sendHeader(Object message) {
        String msg = message.toString();
        logger.info("send fanout message:"+msg);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1", "value1");
        properties.setHeader("header2", "value02");
        Message obj = new Message(msg.getBytes(), properties);
        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);
    }



}
