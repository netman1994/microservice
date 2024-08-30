package org.ywk.common.mq.config.produce;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;



    public void sendMessage(String msg) {
        // rabbitTemplate.send(); todo
    }

}
