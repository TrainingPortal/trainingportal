package trainingportal.model;

public class UserChat {

    private Long id;

    private Long userId;

    private Long chatId;

    public UserChat() {
    }

    public UserChat(Long id, Long userId, Long chatId) {
        this.id = id;
        this.userId = userId;
        this.chatId = chatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", chatId=" + chatId +
                '}';
    }
}
