package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    public static final String SELECT_SQL
            = "SELECT r.roleId, (CASE r.name " +
                "WHEN 'ROLE_ADMIN' THEN 'Admin' " +
                "WHEN 'ROLE_EMPLOYEE' THEN 'Employee' " +
                "WHEN 'ROLE_TRAINER' THEN 'Trainer' " +
                "WHEN 'ROLE_MANAGER' THEN 'Manager' " +
                "END) AS name " +
            "FROM roles r ";

    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {

        Long roleId = rs.getLong("roleId");
        String roleName = rs.getString("name");


        return new Role(roleId, roleName);
    }
}
