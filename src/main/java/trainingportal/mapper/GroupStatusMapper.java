package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.CourseStatus;
import trainingportal.model.GroupStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupStatusMapper implements RowMapper<GroupStatus> {

    public static final String SELECT_SQL
            = "SELECT s.id, s.name_status FROM GroupStatus s ";

    @Override
    public GroupStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long statusId = resultSet.getLong("id");
        String statusName = resultSet.getString("name_status");

        return new GroupStatus(statusId, statusName);
    }
}
