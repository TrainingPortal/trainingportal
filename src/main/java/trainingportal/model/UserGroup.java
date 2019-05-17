package trainingportal.model;

public class UserGroup {

    private Long id;

    private Long groupId;

    private Long userId;

    public UserGroup(Long id, Long groupId, Long userId) {
        this.id = id;
        this.groupId = groupId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
