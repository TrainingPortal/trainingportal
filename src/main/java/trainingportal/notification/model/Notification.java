package trainingportal.notification.model;

import java.util.Date;

public class Notification {

    private Long notificationID;
    private String notificationMessage;
    private Date notificationDate;
    private Long notificationUserID;
    private Long notificationStatusID;

    public Long getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Long notificationID) {
        this.notificationID = notificationID;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Long getNotificationUserID() {
        return notificationUserID;
    }

    public void setNotificationUserID(Long notificationUserID) {
        this.notificationUserID = notificationUserID;
    }

    public Long getNotificationStatusID() {
        return notificationStatusID;
    }

    public void setNotificationStatusID(Long notificationStatusID) {
        this.notificationStatusID = notificationStatusID;
    }

    public Notification(Long notificationID, String notificationMessage, Date notificationDate, Long notificationUserID, Long notificationStatusID) {
        this.notificationID = notificationID;
        this.notificationMessage = notificationMessage;
        this.notificationDate = notificationDate;
        this.notificationUserID = notificationUserID;
        this.notificationStatusID = notificationStatusID;
    }
}
