package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.CourseStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStatusMapper implements RowMapper<CourseStatus> {

    public static final String SELECT_SQL
            = "SELECT s.id, s.name_status FROM CourseStatus s ";

    /*public static final String JOIN_SQL =
            "SELECT b.name_status FROM Course a " +
            "INNER JOIN CourseStatus b " +
            "ON a.course_status_id = b.id ";*/

    @Override
    public CourseStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long statusId = resultSet.getLong("id");
        String statusName = resultSet.getString("name_status");

        return new CourseStatus(statusId, statusName);
    }
}
