package trainingportal.notification.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainingportal.notification.model.Notification;
import trainingportal.notification.model.NotificationStatus;
import trainingportal.notification.service.NotificationService;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/allNotification", method = RequestMethod.GET)
    public void getAllNotification() {
        System.out.println("This is NotificationController");

        List<Notification> allNotificationList = notificationService.findAllNotifications();

        for (Notification noti:allNotificationList) {
            System.out.println(noti.getNotificationMessage());
        }

//        System.out.println(notificationService.findAllNotificationsOfManagers());
//        System.out.println(notificationService.findAllNotificationsOfTrainers());
//        System.out.println(notificationService.findAllNotificationsOfUsers());
//        System.out.println(notificationService.findNotificationByID(5L));
        //System.out.println(notificationService.isNotificationExist(notificationService.findNotificationByID(5L)));
        //System.out.println(notificationService.findNotificationsByStatus(NotificationStatus.POSTED));
        //notificationService.setNotificationMessage(notificationService.findNotificationByID(5L),"new notification");
    }
}
