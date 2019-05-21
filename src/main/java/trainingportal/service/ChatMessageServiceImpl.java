package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.ChatMessageDAO;
import trainingportal.model.ChatMessage;

import java.util.List;

@Service("chatMessageService")
@Transactional
public class ChatMessageServiceImpl implements ChatMessageService{


    @Autowired
    private ChatMessageDAO chatMessageDAO;
    @Override
    public void saveMessage(ChatMessage chatMessage) {
        chatMessageDAO.save(chatMessage);
    }
    @Override
     public  List<ChatMessage> getMessagesByChatId(Long chatId){

       return chatMessageDAO.getMessagesByChatId(chatId);
    }
}
