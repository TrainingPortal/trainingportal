package trainingportal.notification.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.notification.model.Notification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class NotificationMapper implements RowMapper<Notification> {

    public static final String BASE_SQL
            = "SELECT n.id, n.message, n.data_notification, n.user_id, n.status_id FROM notification n ";

    public static final String UPDATE_SQL_MESSAGE
            = "UPDATE notification SET message = ? ";

    public static final String USERS_LEFT_JOIN_NOTIFICATION = "SELECT * FROM users LEFT OUTER JOIN notification ON notification.user_id = users.user_id ";

    @Override
    public Notification mapRow(ResultSet rs, int i) throws SQLException {

        Long notificationID = rs.getLong("id");
        String notificationMessage = rs.getString("message");
        Date notificationDate = rs.getDate("data_notification");
        Long notificationUserID = rs.getLong("user_id");
        Long notificationStatusID = rs.getLong("status_id");

        return new Notification(notificationID,notificationMessage,notificationDate,notificationUserID,notificationStatusID);
    }
}
