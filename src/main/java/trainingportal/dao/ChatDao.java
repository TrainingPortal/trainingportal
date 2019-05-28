package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Chat;
import java.util.List;

public interface ChatDao extends GenericDao<Chat> {
    List<Chat> getAllAsPage(int page, int rowsPerPage);
    int countAll();

    String getChatNameById(Long chatId);
   void saveGroupChat(Chat chat);
   Long getChatByGroupId(Long groupId);
    boolean ifChatExistsByUsersId(Long senderId, Long receiverId);
    Chat findChatIdByUsersId(Long senderId, Long receiverId);
    Chat findChatByName(String chatName);
    List<Chat> findAllPersonalChatsByUserId(Long userId);


}
