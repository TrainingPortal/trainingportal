package trainingportal.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Group {

    private Long groupId;

    @Size(min = 1, max = 70, message = "Please enter group name")
    private String groupName;

    @NotEmpty(message = "Please enter group capacity")
    private int groupCapacity;

    @NotNull
    private Long courseId;

    @NotNull
    private Long statusId;
    
    

    public Group() {
    }

    public Group(Long groupId, String groupName, int groupCapacity, Long courseId, Long statusId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupCapacity = groupCapacity;
        this.courseId = courseId;
        this.statusId = statusId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupCapacity() {
        return groupCapacity;
    }

    public void setGroupCapacity(int groupCapacity) {
        this.groupCapacity = groupCapacity;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
    
}
