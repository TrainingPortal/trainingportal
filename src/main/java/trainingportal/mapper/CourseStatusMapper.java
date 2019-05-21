package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.CourseStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CourseStatusMapper implements BaseObjectMapper<CourseStatus> {

    public static final String SELECT_SQL
            = "SELECT s.id, s.name_status FROM Course_Status s ";

    @Override
    public CourseStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long statusId = resultSet.getLong("id");
        String statusName = resultSet.getString("name_status");

        return new CourseStatus(statusId, statusName);
    }

    @Override
    public Map<String, Object> mapObject(CourseStatus obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("id", obj.getStatusId());

        res.put("name_status",obj.getStatusName());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}
