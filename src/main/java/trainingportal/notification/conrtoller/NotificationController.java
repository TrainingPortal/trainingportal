package trainingportal.notification.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.notification.model.Notification;
import trainingportal.notification.service.NotificationService;
import trainingportal.notification.model.NotificationString;
import trainingportal.security.UserSecurity;

import java.util.Collections;
import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserSecurity userSecurity;

    private Long loggedInUser;

    @GetMapping("notification/allNotification")
    public ModelAndView getNotificationString(ModelAndView modelAndView){

        loggedInUser = userSecurity.getLoggedInUserId();
        List<Notification> allNotificationList = notificationService.findAllNotifications();
        Collections.sort(allNotificationList, Collections.reverseOrder());
        modelAndView.addObject("allNotificationList", allNotificationList);

        return modelAndView;
    }

    @MessageMapping("/notiString")
    @SendTo("/topic/notificationString")
    @RequestMapping(value = "notification/allNotification", method = RequestMethod.POST)
    public NotificationString notificationString(@RequestParam String notificationMessage) {

        Notification notification = new Notification(notificationMessage,loggedInUser);
        notificationService.saveNewNotification(notification);

        return new NotificationString(notification.getNotificationMessage());
    }
}
