package trainingportal.model;

public class GroupStatus {

    private Long statusId;
    private String statusName;

    public GroupStatus() {
    }

    public GroupStatus(Long statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
