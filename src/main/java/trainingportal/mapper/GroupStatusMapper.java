package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.GroupStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GroupStatusMapper implements BaseObjectMapper<GroupStatus> {

    public static final String SELECT_SQL
            = "SELECT s.id, s.name_status FROM Group_Status s ";

    @Override
    public GroupStatus mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long statusId = resultSet.getLong("id");
        String statusName = resultSet.getString("name_status");

        return new GroupStatus(statusId, statusName);
    }

    @Override
    public Map<String, Object> mapObject(GroupStatus obj) {
        Map<String, Object> res = new HashMap<>();
        res.put("id", obj.getStatusId());
        res.put("name_status", obj.getStatusName());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}
