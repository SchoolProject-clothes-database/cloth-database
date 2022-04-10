package se.iths.clothdatabase.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.iths.clothdatabase.rabbitmq.config.MQConfig;

import java.util.Date;
import java.util.UUID;

@Component
public class MessageSender {

    Logger log = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private RabbitTemplate template;


    public void sendMessage(String message) {
        log.info("Sending message...");

        MessageObject messageObject = new MessageObject(UUID.randomUUID().toString(),message, new Date());
        template.convertAndSend(MQConfig.CLOTH_EXCHANGE,
                MQConfig.ROUTING_KEY, messageObject);

        log.info("Message sent!");
    }
}
