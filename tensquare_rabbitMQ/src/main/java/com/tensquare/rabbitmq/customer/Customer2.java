package com.tensquare.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "yonyou2")//证明customer2是消费者,并制定队列名称
public class Customer2 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("分列模式yonyou2：" + msg);
    }

}
