package se.iths.clothdatabase.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    Logger log = LoggerFactory.getLogger(Receiver.class);

    @JmsListener(destination = "{cloth.queue}")
    public void listen(@Payload MessageObject message) {
        log.info("Received message: " + message);
    }
}
