package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.ChatMessage;

import java.util.List;

public interface ChatMessageDAO extends GenericDao<ChatMessage> {

   List<ChatMessage> getMessagesByChatId(Long chatId);
}
