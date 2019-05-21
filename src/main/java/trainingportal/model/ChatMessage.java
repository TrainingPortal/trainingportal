package trainingportal.model;

import java.util.Date;

public class ChatMessage {

    private Long id;

    private Long senderId;

    private String messageDate;

    private String message;

    private Long chatId;

    private MessageType type;

    private String sender;



    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }


    public ChatMessage() {
    }

    public ChatMessage(Long id, Long senderId, String messageDate, String message, Long chatId) {
        this.id = id;
        this.senderId = senderId;
        this.message = message;
        this.chatId = chatId;
        this.messageDate = messageDate;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


}
