package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagersForTrainerMapper implements RowMapper<Long> {

    @Override
    public Long mapRow(ResultSet resultSet, int i) throws SQLException {

        Long courseId = resultSet.getLong("course_id");
        return courseId;
    }
}
