package com.tensquare.test;

import com.tensquare.rabbitmq.RabbtimqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=RabbtimqApplication.class)
public class ProductTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式
     */
    @Test
    public void testSend1(){
        rabbitTemplate.convertAndSend("yonyoua","我要红包");
    }

    /**
     * 分列模式
     */
    @Test
    public void testSend2(){
        rabbitTemplate.convertAndSend("yonyou","", "我是交换器,分列模式");
    }

    /**
     * 主题模式
     */
    @Test
    public void testSend3(){
        //只向yonyou1发送消息
        rabbitTemplate.convertAndSend("topic_yonyou","good.log", "我是交换器,主题模式");
    }
}
