package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.ChatMapper;
import trainingportal.model.Chat;

import javax.sql.DataSource;
import java.util.List;
@Repository
@Transactional
public class ChatDaoImpl extends JdbcDaoSupport implements ChatDao {

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
    public List<Chat> getAllAsPage(int page, int rowsPerPage) {
        String sql = ChatMapper.SELECT_SQL + " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";
        return this.getJdbcTemplate().query(sql, new Object[]{}, new ChatMapper());
    }




    @Override
    public int countAll() {
        String sql = "SELECT COUNT(id) FROM Chat";

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public String getChatNameById(Long chatId) {
        String sql = "SELECT chat_name FROM Chat where Chat.id = ?";
        return this.getJdbcTemplate().queryForObject(sql,new Object[]{chatId}, String.class);
    }
}
