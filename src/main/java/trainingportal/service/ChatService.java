package trainingportal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trainingportal.model.Chat;
import trainingportal.service.generic.GenericService;

import java.util.List;

@Service("chatService")
@Transactional
public interface ChatService extends GenericService<Chat> {

  //  List<Chat> getChatPage(int page, int total);
    //int getPages(double total);

    List<Chat> getChatPage(int page, int rowsPerPage);
    int getPages(double rowsPerPage);
    String getChatNameById(Long chatId);
    void saveGroupChat(Chat chat);
    Long getChatByGroupId(Long groupId);
    boolean ifChatExistsByUsersId(Long senderId, Long receiverId);
    Chat findChatByName(String chatName);
    Chat findChatByUsersId(Long senderId, Long receiverId);
    List<Chat> findAllPersonalChatsByUserId(Long userId);


}
