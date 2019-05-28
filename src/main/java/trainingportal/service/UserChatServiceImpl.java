package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.UserChatDao;
import trainingportal.model.UserChat;

import java.util.List;
@Service("userChatService")
@Transactional
public class UserChatServiceImpl implements UserChatService{

    @Autowired
    private UserChatDao userChatDao;


    @Override
    public UserChat findById(Long id) {
        return null;
    }

    @Override
    public void save(UserChat userChat) {

            userChatDao.save(userChat);

    }

    @Override
    public void update(UserChat entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<UserChat> findAll() {
        return null;
    }

    @Override
    public List<UserChat> getAllAsPage(int page, int total) {
        return null;
    }

    @Override
    public UserChat getUserChatByChatId(Long chatId) {
        return userChatDao.getUserChatByChatId(chatId);
    }
}
