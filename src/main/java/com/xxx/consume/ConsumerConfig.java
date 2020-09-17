package com.xxx.consume;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ConsumerConfig {


    @JmsListener(destination = "ActiveMQQueue") // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @SendTo("SQueue") // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    public void handleMessage(String name) {
        System.out.println("成功接受Name：" + name);
    }


}
