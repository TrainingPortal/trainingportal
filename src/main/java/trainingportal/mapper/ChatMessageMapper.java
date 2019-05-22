package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Chat;
import trainingportal.model.ChatMessage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatMessageMapper implements RowMapper<ChatMessage> {
    public static final String SELECT_SQL
            = "SELECT id, sender_id, date_message, message, chat_id FROM Chat_Message";

//    public static final String EDIT_SQL
//            = "UPDATE Chat SET  chatName = ?";

    public static final String INSERT_SQL
            = "INSERT INTO Chat_Message (sender_id, date_message, message, chat_id) VALUES (?,?,?,?)";

    public static final String DELETE
            ="DELETE FROM Chat_Message";

    @Override
    public ChatMessage mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");

        Long sender_id = resultSet.getLong("sender_id");
        String date_message = resultSet.getString("date_message");

        String message = resultSet.getString("message");

        Long chat_id = resultSet.getLong("chat_id");

        return new ChatMessage(id, sender_id, date_message, message, chat_id);
    }
}
