package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Chat;
import trainingportal.model.Course;

import java.util.List;

public interface ChatDao extends GenericDao<Chat> {
    List<Chat> getAllAsPage(int page, int total);
    int countAll();
    String getChatNameById(Long chatId);
}
