package trainingportal.service;

import trainingportal.model.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    void saveMessage(ChatMessage chatMessage);
    List<ChatMessage> getMessagesByChatId(Long chatId);
}
