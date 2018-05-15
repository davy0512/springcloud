package com.mooc.house.user.rabbitmq;

import com.mooc.house.user.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/817:16
 */
@Service
public class MQReceiver {
    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
    /**
     * Direct 模式   交换机exchangs
     * @param message
     */
    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String message){
        log.info("receive 接受消息："+message);
    }

    /**
     * topic 模式   交换机exchangs
     * @param message
     */
    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic(String message){
        log.info("receive 接受消息："+message);
    }

    /**
     * topic 模式   交换机exchangs
     * @param message
     */
    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message){
        log.info("receive 接受消息："+message);
    }

//    /**
//     * Fanout（广播） 模式   交换机exchangs
//     * @param message
//     */
//    @RabbitListener(queues = MQConfig.FANOUT_EXCHANGE)
//    public void receiveFanout(String message){
//        log.info("receive 接受消息："+message);
//    }

    @RabbitListener(queues=MQConfig.HEADER_QUEUE)
    public void receiveHeaderQueue(byte[] message) {
        log.info(" header  queue message:"+new String(message));
    }


}
