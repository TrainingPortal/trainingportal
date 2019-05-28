package trainingportal.model;

import javax.validation.constraints.NotEmpty;

public class Chat {

    private Long id;
    @NotEmpty(message = "Please enter Forum name")
    private String chatName;

    private Long groupId;


    public Chat(){

    }

    public Chat(Long id, String chatName, Long groupId) {
        this.id = id;
        this.chatName = chatName;
        this.groupId = groupId;
    }

    public Chat(String chatName, Long groupId) {
        this.chatName = chatName;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", chatName='" + chatName + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
