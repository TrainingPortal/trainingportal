package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.ChatDao;
import trainingportal.model.Chat;
import trainingportal.service.generic.GenericService;

import java.util.List;

@Service("chatService")
@Transactional
public interface ChatService extends GenericService<Chat> {

    List<Chat> getChatPage(int page, int rowsPerPage);
    int getPages(double rowsPerPage);
    String getChatNameById(Long chatId);

}
