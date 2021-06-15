//package com.xxx.controller;
//
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import javax.jms.Queue;
//
//@RestController
//public class ProviderController {
//
//    @Resource
//    private Queue queue;
//
//    @Resource
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @RequestMapping("send")
//    public void send(String name) {
//        //添加消息到消息队列
//        jmsMessagingTemplate.convertAndSend(queue, name);
//    }
//}
