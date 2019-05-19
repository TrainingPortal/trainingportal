package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Course;
import trainingportal.model.Lesson;
import trainingportal.model.UserGroup;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGroupMapper implements RowMapper<UserGroup> {

    public static String SELECT_USERS_BY_COURSE_ID_SQL
            = "SELECT a.id, a.group_id, a.user_id FROM User_Group a INNER JOIN Groups b ON a.group_id = b.id " +
            "INNER JOIN Course c ON b.course_id = c.course_id " +
            "WHERE c.course_id = ?";

    public static String SELECT_USERS_BY_LESSON_ID_SQL
            = "SELECT d.id, d.group_id, d.user_id FROM Lesson a " +
            "INNER JOIN Course b ON a.course_id = b.course_id " +
            "INNER JOIN Groups c ON b.course_id = c.course_id " +
            "INNER JOIN User_Group d ON c.id = d.group_id " +
            "WHERE a.lesson_id = ?";

    @Override
    public UserGroup mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long id = rtS.getLong("id");
        Long groupId = rtS.getLong("group_id");
        Long userId = rtS.getLong("user_id");

        return new UserGroup(id, groupId, userId);
    }
}
