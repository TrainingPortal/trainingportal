package trainingportal.model;

public class UserGroup {
    private Long userGroupId;
    private User user;
    private Group group;
    private Long userId;
    private Long groupId;

    public UserGroup(Long userGroupId, Long userId, Long groupId) {
        this.userGroupId = userGroupId;
        this.userId = userId;
        this.groupId = groupId;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
