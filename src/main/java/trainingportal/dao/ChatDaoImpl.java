package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.ChatMapper;
import trainingportal.model.Chat;

import javax.sql.DataSource;
import java.util.List;
@Repository
@Transactional
public class ChatDaoImpl extends GenericDaoImpl<Chat> implements ChatDao {

    @Autowired
    public ChatDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public Chat findById(Long id) {
        return null;
    }

    @Override
    public void save(Chat chat) {
        String sql = "INSERT INTO chat (chat_name) VALUES (?)";
        this.getJdbcTemplate().update(sql, new Object[]{chat.getChatName()});
    }

    @Override
    public void update(Chat entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Chat> findAll() {
        return null;
    }

    @Override
    public List<Chat> getAllAsPage(int page, int total) {
        String sql = "SELECT c.id, c.chat_name, c.group_id FROM Chat c where c.id not in (select ch.id from chat ch " +
                "inner join user_chat uc on uc.chat_id = ch.id) and c.group_id is null order by c.id "
                + " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";
        return this.getJdbcTemplate().query(sql, new Object[]{}, new ChatMapper());
    }

    @Override
    protected BaseObjectMapper<Chat> getObjectMapper() {
        return null;
    }

    @Override
    public int countAll() {

        String sql = "SELECT COUNT(id) FROM Chat  c where c.id not in (select c.id from chat c inner join user_chat uc" +
                " on uc.chat_id = c.id) and c.group_id is null order by c.id";

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public String getChatNameById(Long chatId) {
        String sql = "SELECT chat_name FROM Chat where Chat.id = ?";

            return this.getJdbcTemplate().queryForObject(sql,new Object[]{chatId}, String.class);

    }

    @Override
    public void saveGroupChat(Chat chat) {
        String sql = ChatMapper.INSERT_SQL;
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, new Object[]{chat.getChatName(), chat.getGroupId()});
        }
    }

    @Override
    public Long getChatByGroupId(Long groupId) {

        String sql = "SELECT id from CHAT where group_id = ?";


        return  this.getJdbcTemplate().queryForObject(sql, new Object[]{groupId}, Long.class);
    }

    @Override
    public boolean ifChatExistsByUsersId(Long senderId, Long receiverId) {

        String sql  = "SELECT c.id, c.chat_name, group_id FROM Chat c INNER " +
                "JOIN User_Chat uc on uc.chat_id = c.id WHERE uc.sender_id = ? AND uc.receiver_id = ?";

        List<Chat> chats = null;
        if (this.getJdbcTemplate() != null) {
            chats = this.getJdbcTemplate().query(sql,new Object[]{senderId, receiverId},new ChatMapper());
        }
        return chats != null && chats.size() > 0;
    }

    @Override
    public Chat findChatIdByUsersId(Long senderId, Long receiverId) {
      //  String sql = ChatMapper.SELECT_SQL + " WHERE sender_id = ? AND receiverId = ?";
        String sql = "SELECT c.id, c.chat_name, group_id FROM Chat c INNER " +
                "JOIN User_Chat uc on uc.chat_id = c.id WHERE uc.sender_id = ? AND uc.receiver_id = ?";

            return   this.getJdbcTemplate().queryForObject(sql, new Object[]{senderId, receiverId}, new ChatMapper());


    }

    @Override
    public Chat findChatByName(String chatName) {
        String sql = ChatMapper.SELECT_SQL+" WHERE chat_name = ?";

            return this.getJdbcTemplate().queryForObject(sql, new Object[]{chatName}, new ChatMapper());

    }

    @Override
    public List<Chat> findAllPersonalChatsByUserId(Long userId) {

        String sql = " SELECT c.id, c.chat_name, c.group_id FROM chat c INNER JOIN user_chat uc ON uc.chat_id = c.id " +
                "WHERE uc.sender_id = ? OR uc.receiver_id = ? ORDER BY c.id ";

        return this.getJdbcTemplate().query(sql, new Object[]{userId, userId}, new ChatMapper());
    }

}
