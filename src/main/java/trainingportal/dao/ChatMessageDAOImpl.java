package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.ChatMessageMapper;
import trainingportal.mapper.UserMapper;
import trainingportal.model.ChatMessage;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import trainingportal.model.User;

import javax.sql.DataSource;

import javax.sql.DataSource;
import java.util.List;
@Repository
@Transactional
public class ChatMessageDAOImpl extends JdbcDaoSupport implements ChatMessageDAO{
    @Autowired
    public ChatMessageDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public ChatMessage findById(Long id) {
        return null;
    }

    @Override
    public void save(ChatMessage chatMessage) {
        String sql = ChatMessageMapper.INSERT_SQL;
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, chatMessage.getSenderId(),chatMessage.getMessageDate(), chatMessage.getMessage(), chatMessage.getChatId());
        }
    }

    @Override
    public void update(ChatMessage entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<ChatMessage> findAll() {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }

    @Override
    public List<ChatMessage> getAllAsPage(int page, int total) {
        return null;
    }

    @Override
    public List<ChatMessage> getMessagesByChatId(Long chatId) {
        String sql = ChatMessageMapper.SELECT_SQL + " WHERE chat_id = ?";
        List<ChatMessage> messagesList = this.getJdbcTemplate().query(sql, new Object[]{chatId}, new ChatMessageMapper());
        for (ChatMessage message:messagesList
             ) {
            message.setSender(getUsernameByMessageId(message.getId()));

        }
        return messagesList;
    }

    public String getUsernameByMessageId(Long messageId){
        String sql = "SELECT name FROM users u INNER JOIN Chat_Message cm on u.user_id = cm.sender_id where cm.id = ?";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{messageId}, String.class);
    }
}
