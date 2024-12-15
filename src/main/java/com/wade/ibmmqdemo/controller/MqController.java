package com.wade.ibmmqdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqController {

    public static final Logger log = LoggerFactory.getLogger(MqController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/send")
    public String send(@RequestParam String sendMsg){
        try {
            jmsTemplate.convertAndSend("DEV.QUEUE.1", sendMsg);
            log.info("send msg：" + sendMsg + " OK");
            return "OK";
        } catch(JmsException ex) {
            ex.printStackTrace();
            return "FAIL";
        }
    }

    @GetMapping("/recv")
    public String recv(){
        try{
            return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
        }catch(JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }
    }
}
