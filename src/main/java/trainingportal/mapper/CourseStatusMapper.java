package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.CourseStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStatusMapper implements RowMapper<CourseStatus> {
    @Override
    public CourseStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long statusId = resultSet.getLong("statusId");
        String statusName = resultSet.getString("statusName");

        return new CourseStatus(statusId, statusName);
    }
}
