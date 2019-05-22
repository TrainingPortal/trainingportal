package trainingportal.notification.websocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;
import trainingportal.notification.model.Notification;
import trainingportal.notification.service.NotificationService;

@Controller
public class GreetingController {

    @Autowired
    NotificationService notificationService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @RequestMapping(value = "notification/allNotification", method = RequestMethod.GET)
    public Greeting greeting(Notification message) throws Exception {
        //Thread.sleep(1000); // simulated delay
        message = notificationService.findNotificationByID(4L);
        //String m = message.getNotificationMessage();
        //HtmlUtils.htmlEscape(message.getName())
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getNotificationMessage().toString()) + "!");
    }
}
