package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChatMapper implements BaseObjectMapper<Chat> {

    public static final String SELECT_SQL
            = "SELECT id, chat_name, group_id FROM Chat";

    public static final String EDIT_SQL
            = "UPDATE Chat SET  chat_name = ?";

    public static final String INSERT_SQL
            = "INSERT INTO Chat (chat_name, group_id) VALUES (?,?)";

    public static final String DELETE
            ="DELETE FROM Chat";

    @Override
    public Chat mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");

        String chat_name = resultSet.getString("chat_name");

        Long groupId = resultSet.getLong("group_id");

        return new Chat(id, chat_name, groupId);
    }

    @Override
    public Map<String, Object> mapObject(Chat obj) {
        return null;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}
