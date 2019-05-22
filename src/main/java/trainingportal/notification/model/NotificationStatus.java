package trainingportal.notification.model;

public class NotificationStatus {

    public static final Long POSTED = 1L;
    public static final Long NOT_POSTED = 2L;
    public static final Long OPENED = 4L;
    public static final Long CLOSED = 5L;

    private Long notificationStatusID;
    private String notificationStatusName;

    public Long getNotificationStatusID() {
        return notificationStatusID;
    }

    public void setNotificationStatusID(Long notificationStatusID) {
        this.notificationStatusID = notificationStatusID;
    }

    public String getNotificationStatusName() {
        return notificationStatusName;
    }

    public void setNotificationStatusName(String notificationStatusName) {
        this.notificationStatusName = notificationStatusName;
    }
}
