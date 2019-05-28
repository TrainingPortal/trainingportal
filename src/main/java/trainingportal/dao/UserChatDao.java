package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.UserChat;

public interface UserChatDao  extends GenericDao<UserChat> {
    UserChat getUserChatByChatId(Long chatId);
}
