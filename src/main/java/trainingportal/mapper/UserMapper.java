package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserMapper implements BaseObjectMapper<User> {
 
    public static final String BASE_SQL
            = "SELECT u.userId, u.name, u.email, u.password, u.enabled, u.token, u.roleId, u.managerId FROM users u ";
    public static final String UPDATE_SQL
            = "UPDATE users SET name = ?, email = ?, enabled = ?, roleId = ?";
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("userId");
        String userName = rs.getString("name");
        String userEmail = rs.getString("email");
        String password = rs.getString("password");
        int enabled = rs.getInt("enabled");
        String token = rs.getString("token");
        Long roleId = rs.getLong("roleId");
        Long managerId = rs.getLong("managerId");

        return new User(userId, userName,userEmail, password, enabled, token, roleId, managerId);
    }

    @Override
    public Map<String, Object> mapObject(User obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("userId", obj.getUserId());
        res.put("name", obj.getUserName());
        res.put("email", obj.getEmail());
        res.put("password", obj.getPassword());
        res.put("enabled", obj.getEnabled());
        res.put("token", obj.getToken());
        res.put("roleId",obj.getRoleId());
        res.put("managerId", obj.getManagerId());

        return  res;
    }
}