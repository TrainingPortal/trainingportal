package trainingportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.Chat;
import trainingportal.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import trainingportal.model.LoggedInUser;
import trainingportal.model.User;
import trainingportal.security.UserSecurity;
import trainingportal.service.ChatMessageService;
import trainingportal.service.ChatService;
import trainingportal.service.UserService;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class ChatController {
    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatService chatService;

    private static final int ROWS_PER_PAGE = 10;


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");
        String date = ft.format(dNow);
        chatMessage.setMessageDate(date);
        chatMessageService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @RequestMapping(value = "/chat/{chatId}")
    public String openChat(@PathVariable("chatId") Long chatId, Model model, Authentication authentication){

         model.addAttribute("user", authentication.getPrincipal());
         model.addAttribute("id", getLoggedInUserId());
         model.addAttribute("chatId", chatId);
         List<ChatMessage> messagesFromChat = chatMessageService.getMessagesByChatId(chatId);
         String chatName = chatService.getChatNameById(chatId);
         model.addAttribute("messagesFromChat", messagesFromChat);
         model.addAttribute("chatName", chatName);

        return "frontend/chat";
}

    @RequestMapping(value = "/show_chats/{page}")
    public ModelAndView openChatList(@PathVariable("page") int page, ModelAndView modelAndView){

        List<Chat> chatsList = chatService.getChatPage(page, ROWS_PER_PAGE);
        modelAndView.addObject("chatsList", chatsList);
        modelAndView.addObject("pages", chatService.getPages(ROWS_PER_PAGE));
        modelAndView.setViewName("chatCreator/show_chats");
        modelAndView.addObject("currentUrl", "show_chats");

        return modelAndView;
    }

    @RequestMapping(value = "/create-chat", method = RequestMethod.GET)
    public ModelAndView createChat(ModelAndView modelAndView) {

        modelAndView.addObject("chat", new Chat());
        modelAndView.setViewName("chatCreator/create_chat");

        return modelAndView;
    }

    @RequestMapping(value = "save-chat", method = RequestMethod.POST)
    public ModelAndView saveChat(Chat chat, ModelAndView modelAndView) {

        chatService.save(chat);
        modelAndView.setViewName("redirect:/show_chats/1");
        return modelAndView;
    }

    private Long getLoggedInUserId(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ((LoggedInUser)auth.getPrincipal()).getId();
    }


}
