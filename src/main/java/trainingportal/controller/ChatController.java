package trainingportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trainingportal.model.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import trainingportal.service.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;
    @Autowired
    private GroupService groupService;

    @Autowired
     private UserChatService userChatService;

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

    @RequestMapping(value = "create-chat-for-groups/{courseId}")
    public ModelAndView createChatForGroups(@PathVariable("courseId")Long courseId, ModelAndView modelAndView){

        List<Group> groups = groupService.findAllGroupsWithoutChatByCourseId(courseId);

        for (Group group:groups
             ) {
            Chat chat = new Chat(group.getGroupName(), group.getGroupId());
            chatService.saveGroupChat(chat);
        }
        modelAndView.setViewName("redirect:/create_group_chats/1/1");

        return modelAndView;
    }

    @RequestMapping(value = "save-chat", method = RequestMethod.POST)
    public ModelAndView saveChat(Chat chat, ModelAndView modelAndView) {

        chatService.save(chat);
        modelAndView.setViewName("redirect:/show_chats/1");
        return modelAndView;
    }
    @RequestMapping(value = "open-group-chat/{groupId}")
    public ModelAndView openGroupChat(@PathVariable("groupId") Long groupId, ModelAndView modelAndView, Authentication authentication){

        modelAndView.addObject("user", authentication.getPrincipal());
        modelAndView.addObject("id", getLoggedInUserId());
        Long chatId = chatService.getChatByGroupId(groupId);
        List<ChatMessage> messagesFromChat = chatMessageService.getMessagesByChatId(chatId);
        String chatName = chatService.getChatNameById(chatId);
        modelAndView.addObject("chatId", chatId);
        modelAndView.addObject("messagesFromChat", messagesFromChat);
        modelAndView.addObject("chatName", chatName);
        modelAndView.setViewName("frontend/chat");
        return modelAndView;
    }

    @RequestMapping(value = "open-personal-chat-list")
    public ModelAndView openPersonalChatList(ModelAndView modelAndView){

        Long userId = getLoggedInUserId();
        List<Chat> chatsList = chatService.findAllPersonalChatsByUserId(userId);
        modelAndView.addObject("chatsList", chatsList);
        modelAndView.setViewName("chatCreator/show_personal_chats");
        modelAndView.addObject("currentUrl", "show_personal_chats");
        return modelAndView;
    }


    @RequestMapping(value = "open-personal-chat/{receiverId}")
    public ModelAndView openPersonalChat(@PathVariable("receiverId") Long receiverId, ModelAndView modelAndView,  Authentication authentication){

        Long senderId = getLoggedInUserId();
        boolean chatExists =  chatService.ifChatExistsByUsersId(senderId, receiverId);
        if(!chatExists){
            User sender = userService.findById(senderId);
            User receiver = userService.findById(receiverId);
            Chat chat = new Chat();
            String chatName = sender.getUserName().concat(" / "+receiver.getUserName());
            chat.setChatName(chatName);
            chatService.save(chat);
            Long chatId = chatService.findChatByName(chatName).getId();
            UserChat userChat = new UserChat(senderId, receiverId, chatId);
            userChatService.save(userChat);
        }
        Long chatId = chatService.findChatByUsersId(senderId, receiverId).getId();
        List<ChatMessage> messagesFromChat = chatMessageService.getMessagesByChatId(chatId);
        modelAndView.addObject("user", authentication.getPrincipal());
        modelAndView.addObject("id", senderId);
        modelAndView.addObject("chatId", chatId);
        String chatName = chatService.getChatNameById(chatId);
        modelAndView.addObject("messagesFromChat", messagesFromChat);
        modelAndView.addObject("chatName", chatName);
        modelAndView.setViewName("frontend/chat");

    return modelAndView;

    }

    @GetMapping("/search_users")
    public ModelAndView searchUsers(ModelAndView model) {

        List<User> users = userService.findAll();
        model.addObject("users", users);
        model.setViewName("chatCreator/search_users");

        return model;
    }

    private Long getLoggedInUserId(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ((LoggedInUser)auth.getPrincipal()).getId();
    }

}
