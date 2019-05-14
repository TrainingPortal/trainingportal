package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GroupMapper implements BaseObjectMapper<Group> {

    public static final String SELECT_SQL
            = "SELECT id, name, capacity, course_id, status_id FROM groups";

    public static final String EDIT_SQL
            = "UPDATE groups SET name = ?, capacity = ?, course_id = ?, status_id = ?";


    @Override
    public Group mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long groupId = resultSet.getLong("id");

        String groupName = resultSet.getString("name");

        int groupCapacity = resultSet.getInt("capacity");

        Long courseId = resultSet.getLong("course_id");

        Long statusId = resultSet.getLong("status_id");
        

        return new Group(groupId, groupName, groupCapacity, courseId, statusId);
    }

    @Override
    public Map<String, Object> mapObject(Group obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("id", obj.getGroupId());

        res.put("name",obj.getGroupName());

        res.put("capacity", obj.getGroupCapacity());

        res.put("course_id", obj.getCourseId());

        res.put("status_id", obj.getStatusId());

        return res;
    }
}
