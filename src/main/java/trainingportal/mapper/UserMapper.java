package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
 
    public static final String BASE_SQL
            = "SELECT u.userId, u.name, u.email, u.password, u.enabled, u.token, u.roleId FROM users u ";
    public static final String UPDATE_SQL
            = "UPDATE users SET name = ?, email = ?, enabled = ?, roleId = ?, password = ? ";
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("userId");
        String userName = rs.getString("name");
        String userEmail = rs.getString("email");
        String password = rs.getString("password");
        int enabled = rs.getInt("enabled");
        String token = rs.getString("token");
        Long roleId = rs.getLong("roleId");

        return new User(userId, userName,userEmail, password, enabled, token, roleId);
    }
}