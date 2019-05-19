package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserMapper implements BaseObjectMapper<User> {
 
    public static final String SELECT_SQL
            = "SELECT u.user_id, u.name, u.email, u.password, u.enabled, u.token, u.role_id, u.manager_id FROM users u ";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("user_id");
        String userName = rs.getString("name");
        String userEmail = rs.getString("email");
        String password = rs.getString("password");
        int enabled = rs.getInt("enabled");
        String token = rs.getString("token");
        Long roleId = rs.getLong("role_id");
        Long managerId = rs.getLong("manager_id");

        return new User(userId, userName,userEmail, password, enabled, token, roleId, managerId);
    }

    @Override
    public Map<String, Object> mapObject(User obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("user_id", obj.getUserId());
        res.put("name", obj.getUserName());
        res.put("email", obj.getEmail());
        res.put("password", obj.getPassword());
        res.put("enabled", obj.getEnabled());
        res.put("token", obj.getToken());
        res.put("role_id",obj.getRoleId());
        res.put("manager_id", obj.getManagerId());

        return  res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}