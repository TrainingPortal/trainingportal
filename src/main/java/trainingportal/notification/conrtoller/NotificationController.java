package trainingportal.notification.conrtoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.notification.model.Notification;
import trainingportal.notification.service.NotificationService;
import trainingportal.notification.model.NotificationString;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void getAllNotification() {
        System.out.println("This is NotificationController");

        List<Notification> allNotificationList = notificationService.findAllNotifications();

    }

//        message = notificationService.findNotificationByID(4L);
//        HtmlUtils.htmlEscape();

    @GetMapping("notification/allNotification")
    public ModelAndView getNotificationString(ModelAndView modelAndView){

        modelAndView.setViewName("notification/allNotification");
        return modelAndView;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/notificationString")
    @RequestMapping(value = "notification/allNotification", method = RequestMethod.POST)
    public NotificationString notificationString(String notificationMessage) {

        //todo get user id

        Notification notification = new Notification(notificationMessage,2L);
        notificationService.saveNewNotification(notification);

        return new NotificationString(notification.getNotificationMessage());
    }
}
