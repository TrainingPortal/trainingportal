package trainingportal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.model.UserChat;
import trainingportal.service.generic.GenericService;
@Service("userChatService")
@Transactional
public interface UserChatService extends GenericService<UserChat> {
    UserChat getUserChatByChatId(Long chatId);
}
