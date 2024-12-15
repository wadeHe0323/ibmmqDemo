package com.wade.ibmmqdemo;

import com.wade.ibmmqdemo.controller.MqController;
import jakarta.jms.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class ReceiveMessage extends MessageListenerAdapter {

    public static final Logger log = LoggerFactory.getLogger(ReceiveMessage.class);

    @Override
    @JmsListener(destination = "DEV.QUEUE.1")
    public void onMessage(Message message) {
        log.info("Get message from mq, msg: " + message.toString());
    }
}
