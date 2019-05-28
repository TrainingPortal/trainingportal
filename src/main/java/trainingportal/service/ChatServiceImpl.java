package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.ChatDao;
import trainingportal.model.Chat;

import java.util.List;

@Service("chatService")
@Transactional
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;

    @Override
    public Chat findById(Long id) {
        return null;
    }

    @Override
    public void save(Chat chat) {
        chatDao.save(chat);
    }

    @Override
    public void update(Chat entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Chat> findAll() {
        return null;
    }

    @Override
    public List<Chat> getAllAsPage(int page, int rowsPerPage) {
        return null;
    }


    @Override
    public List<Chat> getChatPage(int page, int rowsPerPage) {
        if(page == 1){
            //do nothing
        } else {
            page = (page - 1) * rowsPerPage + 1;
        }
        List<Chat> chatsList;

        chatsList = chatDao.getAllAsPage(page, rowsPerPage);

        return chatsList;
    }

    @Override
    public int getPages(double rowsPerPage) {
        return (int) Math.ceil(chatDao.countAll() / rowsPerPage);
    }



    @Override
    public String getChatNameById(Long chatId) {
        return chatDao.getChatNameById(chatId);
    }

    @Override
    public void saveGroupChat(Chat chat) {
        chatDao.saveGroupChat(chat);
    }

    @Override
    public Long getChatByGroupId(Long groupId) {

        return  chatDao.getChatByGroupId(groupId);
    }

    @Override
    public boolean ifChatExistsByUsersId(Long senderId, Long receiverId) {
       return chatDao.ifChatExistsByUsersId(senderId,receiverId);
    }

    @Override
    public Chat findChatByName(String chatName) {
        return chatDao.findChatByName(chatName);
    }

    @Override
    public Chat findChatByUsersId(Long senderId, Long receiverId) {
        return chatDao.findChatIdByUsersId(senderId,receiverId);
    }

    @Override
    public List<Chat> findAllPersonalChatsByUserId(Long userId) {
        return chatDao.findAllPersonalChatsByUserId(userId);
    }


}
