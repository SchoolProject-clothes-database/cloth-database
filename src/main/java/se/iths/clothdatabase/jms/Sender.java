package se.iths.clothdatabase.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class Sender {

    Logger log = LoggerFactory.getLogger(Sender.class);

    JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        log.info("Sending message...");

        MessageObject messageObject = new MessageObject(UUID.randomUUID(),message, LocalDateTime.now());
        jmsTemplate.convertAndSend("{cloth.queue}", messageObject);

        log.info("Message sent!");
    }

}
