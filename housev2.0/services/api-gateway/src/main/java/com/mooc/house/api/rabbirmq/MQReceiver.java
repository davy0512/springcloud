package com.mooc.house.api.rabbirmq;

import com.mooc.house.api.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 消息接收者
 * @date 2018/5/814:47
 */
@Service
public class MQReceiver {
    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    /**
     * Direct 模式   交换机exchangs
     * @param message
     */
//    @RabbitListener(queues = MQConfig.QUEUE)
//    public void receive(String message){
//        log.info("receive 接受消息："+message);
//    }



}
