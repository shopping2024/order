package com.ecom.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.messaging.support.MessageBuilder;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;
import com.google.gson.Gson;
@Service
@Slf4j
public class OrderProducer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    public void sendOrderConformation(OrderConformation orderConformation){
        log.info("Sending Order Conformation",orderConformation);

Gson gson=new Gson();
String message=gson.toJson(orderConformation);
   kafkaTemplate.send("order-topic",message);
    }

}
