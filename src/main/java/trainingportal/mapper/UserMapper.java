package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
 
    public static final String BASE_SQL
            = "Select u.User_Id, u.User_Name, u.User_Email, u.Encryted_Password, u.Enabled From App_User u ";
 
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("User_Id");
        String userName = rs.getString("User_Name");
        String userEmail = rs.getString("User_Email");
        String encryptedPassword = rs.getString("Encryted_Password");
        int enabled = rs.getInt("Enabled");

        return new User(userId, userName,userEmail, encryptedPassword, enabled);
    }
 
}