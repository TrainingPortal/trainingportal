package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.UserGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserGroupMapper implements BaseObjectMapper<UserGroup> {

    public static final String BASE_SQL =
            "SELECT * FROM user_group";


    @Override
    public Map<String, Object> mapObject(UserGroup obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("id", obj.getUserGroupId());
        res.put("user_id", obj.getUserId());
        res.put("group_id", obj.getGroupId());

        return res;
    }

    @Override
    public UserGroup mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        Long userId = resultSet.getLong("user_id");
        Long groupId = resultSet.getLong("group_id");

        return new UserGroup(id, userId,groupId);
    }
}
