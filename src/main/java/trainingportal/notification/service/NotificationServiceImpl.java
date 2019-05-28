package trainingportal.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.notification.dao.NotificationDao;
import trainingportal.notification.model.Notification;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public List<Notification> findAllNotifications() {
        return notificationDao.findAllNotifications();
    }

    @Override
    public List<Notification> findAllNotificationsOfUsers() {
        return notificationDao.findAllNotificationsOfUsers();
    }

    @Override
    public List<Notification> findAllNotificationsOfTrainers() {
        return notificationDao.findAllNotificationsOfTrainers();
    }

    @Override
    public List<Notification> findAllNotificationsOfManagers() {
        return notificationDao.findAllNotificationsOfManagers();
    }

    @Override
    public Notification findNotificationByID(Long notificationID) {
        return notificationDao.findNotificationByID(notificationID);
    }

    @Override
    public Boolean isNotificationExist(Notification notificationID) {
        return notificationDao.isNotificationExist(notificationID);
    }

    @Override
    public void setNotificationMessage(Notification notification, String notificationMessage) {
        notificationDao.setNotificationMessage(notification,notificationMessage);
    }

    @Override
    public void saveNewNotification(Notification notification) {
        notificationDao.saveNewNotification(notification);
    }
}
