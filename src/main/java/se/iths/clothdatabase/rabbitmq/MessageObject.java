package se.iths.clothdatabase.rabbitmq;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageObject {

    private String messageId;
    private String message;
    private Date messageDate;

    public MessageObject() {
    }

    public MessageObject(String messageId, String message, Date messageDate) {
        this.messageId = messageId;
        this.message = message;
        this.messageDate = messageDate;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
