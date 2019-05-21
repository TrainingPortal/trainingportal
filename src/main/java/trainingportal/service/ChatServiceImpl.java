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
    public List<Chat> getAllAsPage(int page, int total) {
        return null;
    }


    @Override
    public List<Chat> getChatPage(int page, int total) {
        if(page == 1){
            //do nothing
        } else {
            page = (page - 1) * total + 1;
        }
        List<Chat> chatsList;

        chatsList = chatDao.getAllAsPage(page, total);

        return chatsList;
    }

    @Override
    public int getPages(double total) {
        return (int) Math.ceil(chatDao.countAll() / total);
    }

    @Override
    public String getChatNameById(Long chatId) {
        return chatDao.getChatNameById(chatId);
    }
}
