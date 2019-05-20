package trainingportal.notification.service;

import trainingportal.notification.model.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> findAllNotifications();

    List<Notification> findAllNotificationsOfUsers();

    List<Notification> findAllNotificationsOfTrainers();

    List<Notification> findAllNotificationsOfManagers();

    Notification findNotificationByID(Long notificationID);

    Boolean isNotificationExist(Notification notificationID);

    void setNotificationMessage(Notification notificationID, String notificationMessage);
}
