package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.UserChat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserChatMapper implements BaseObjectMapper<UserChat> {

    public static final String SELECT_SQL
            = "SELECT id, sender_id, receiver_id, chat_id FROM User_Chat";


    public static final String INSERT_SQL
            = "INSERT INTO User_Chat (sender_id, receiver_id, chat_id) VALUES (?,?,?)";


    @Override
    public Map<String, Object> mapObject(UserChat obj) {
        return null;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }

    @Override
    public UserChat mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");

        Long senderId = resultSet.getLong("sender_id");

        Long receiverId = resultSet.getLong("receiver_id");

        Long chatId = resultSet.getLong("chat_id");

        return new UserChat(id, senderId, receiverId, chatId);
    }
}
