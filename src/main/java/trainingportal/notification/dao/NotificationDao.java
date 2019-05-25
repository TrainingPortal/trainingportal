package trainingportal.notification.dao;

import trainingportal.notification.model.Notification;

import java.util.List;

public interface NotificationDao {

    List<Notification> findAllNotifications();

    List<Notification> findAllNotificationsOfUsers();

    List<Notification> findAllNotificationsOfTrainers();

    List<Notification> findAllNotificationsOfManagers();

    Notification findNotificationByID(Long notificationID);

    Boolean isNotificationExist(Notification notificationID);

    void setNotificationMessage(Notification notification, String notificationMessage);

    public void saveNewNotification(Notification notification);

}
