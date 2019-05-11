package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.CourseStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStatusMapper implements RowMapper<CourseStatus> {

    public static final String SELECT_SQL
            = "SELECT s.id, s.name_status FROM CourseStatus s ";

    @Override
    public CourseStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long statusId = resultSet.getLong("id");
        String statusName = resultSet.getString("name_status");

        return new CourseStatus(statusId, statusName);
    }
}
