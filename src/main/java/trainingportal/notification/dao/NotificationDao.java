package trainingportal.notification.dao;

import trainingportal.notification.model.Notification;

import java.util.List;

public interface NotificationDao {

    List<Notification> findAllNotifications();

    List<Notification> findAllNotificationsOfUsers();

    List<Notification> findAllNotificationsOfTrainers();

    List<Notification> findAllNotificationsOfManagers();

    Notification findNotificationByID(Long notificationID);

    List<Notification> findNotificationsByStatus(Long statusID);

    Boolean isNotificationExist(Notification notificationID);

    void setNotificationMessage(Notification notificationID, String notificationMessage);
}
