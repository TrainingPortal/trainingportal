package trainingportal.model;

public class UserChat {

    private Long id;

    private Long senderId;

    private Long receiverId;

    private Long chatId;

    public UserChat() {
    }

    public UserChat(Long id, Long senderId, Long receiverId, Long chatId) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.chatId = chatId;
    }
    public UserChat(Long senderId, Long receiverId, Long chatId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.chatId = chatId;
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

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "UserChat{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", chatId=" + chatId +
                '}';
    }
}
