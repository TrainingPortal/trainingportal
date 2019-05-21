package trainingportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.InfoDesk;
import trainingportal.service.InfoDeskService;
import trainingportal.service.UserService;

import java.util.List;

@Controller
public class InfoDeskController {

    private static final int ROWS_LIMIT = 10;
    @Autowired
    private InfoDeskService infoDeskService;
    @Autowired
    private UserService userService;

    @MessageMapping("/infoDeskChat.sendMessage")
    @SendTo("/topic/public")
    public InfoDesk sendRequest(@Payload InfoDesk infoDesk) {

        System.out.println("sender: "+infoDesk.getSender());
        //   System.out.println(chatMessage.getSenderId()+" "+ chatMessage.getSender()+" : " +chatMessage.getContent().toString());
        infoDeskService.save(infoDesk);
        return infoDesk;
    }

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage,
//                               SimpMessageHeaderAccessor headerAccessor) {
//        // Add username in web socket session
//
//        // chatMessage.setSenderId((Long) getLoggedInUserId());
//
//        // chatMessage.setSenderId(senderId);
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }

    @RequestMapping(value = "/infoDeskChat/{infoDeskId}")
    public String openInfoDesk(@PathVariable("infoDeskId") Long infoDeskId, Model model, Authentication authentication){
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("infoDeskId", infoDeskId);
        List<InfoDesk> messagesFromChat = infoDeskService.getRequestForHelpByEmpId(infoDeskId);
        model.addAttribute("messagesFromChat", messagesFromChat);
        return "frontend/chat";
    }
    @RequestMapping(value = "/show_InfoDesk/{page}")
    public ModelAndView openChatList(@PathVariable("page") int page, ModelAndView modelAndView){
        //  public ModelAndView openChatList(ModelAndView modelAndView){


        List<InfoDesk> chatsList = infoDeskService.getAllAsPage(page, ROWS_LIMIT);

        modelAndView.addObject("chatsList", chatsList);
        modelAndView.addObject("pages", infoDeskService.getPages(ROWS_LIMIT));
        modelAndView.setViewName("infoDesk/show_InfoDesk");
        modelAndView.addObject("currentUrl", "show_InfoDesk");

        return modelAndView;

    }

    @RequestMapping(value = "/create-request", method = RequestMethod.GET)
    public ModelAndView createChat(ModelAndView modelAndView) {
        modelAndView.addObject("infoDesk", new InfoDesk());
        modelAndView.setViewName("infoDesk/create-request");

        return modelAndView;
    }

    @RequestMapping(value = "save-request", method = RequestMethod.POST)
    public ModelAndView saveChat(InfoDesk infoDesk, ModelAndView modelAndView) {
        infoDeskService.save(infoDesk);
        modelAndView.setViewName("redirect:/show_InfoDesk/1");
        return modelAndView;
    }

}
