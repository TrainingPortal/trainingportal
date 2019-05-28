package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.UserChatMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.UserChat;

import javax.sql.DataSource;

@Repository
@Transactional
public class UserChatDaoImpl extends GenericDaoImpl<UserChat> implements UserChatDao {

    @Autowired
    public UserChatDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    @Override
    public void save(UserChat userChat) {
        String sql = "INSERT INTO User_Chat (sender_id, receiver_id, chat_id) VALUES (?,?,?)";
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, new Object[]{userChat.getSenderId(),userChat.getReceiverId(),userChat.getChatId()});
        }
    }
    @Override
    public UserChat getUserChatByChatId(Long chatId){
        String sql = "SELECT User_Chat.id, User_Chat.sender_id, User_Chat.receiver_id, User_Chat.chat_id FROM User_Chat" +
                " INNER JOIN Chat on User_Chat.chat_id = Chat.id where Chat.id = ?";

            return this.getJdbcTemplate().queryForObject(sql, new Object[]{chatId}, new UserChatMapper());

    }
    @Override
    protected BaseObjectMapper<UserChat> getObjectMapper() {
        return null;
    }
}
