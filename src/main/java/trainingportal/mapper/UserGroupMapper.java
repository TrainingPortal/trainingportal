package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.UserGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserGroupMapper implements BaseObjectMapper<UserGroup> {

    public static String SELECT_USERS_SQL
            = "SELECT a.id, a.group_id, a.user_id FROM User_Group a INNER JOIN Groups b ON a.group_id = b.id " +
            "INNER JOIN Course c ON b.course_id = c.course_id " +
            "WHERE c.course_id = ?";


    @Override
    public Map<String, Object> mapObject(UserGroup obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("id", obj.getId());
        res.put("user_id", obj.getUserId());
        res.put("group_id", obj.getGroupId());

        return res;
    }

    @Override
    public UserGroup mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long id = rtS.getLong("id");
        Long groupId = rtS.getLong("group_id");
        Long userId = rtS.getLong("user_id");

        return new UserGroup(id, groupId, userId);
    }
}
