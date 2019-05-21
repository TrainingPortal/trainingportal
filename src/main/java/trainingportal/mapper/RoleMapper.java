package trainingportal.mapper;

import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RoleMapper implements BaseObjectMapper<Role> {

    public static final String SELECT_SQL
            = "SELECT r.role_id, (CASE r.name " +
                "WHEN 'ROLE_ADMIN' THEN 'Admin' " +
                "WHEN 'ROLE_EMPLOYEE' THEN 'Employee' " +
                "WHEN 'ROLE_TRAINER' THEN 'Trainer' " +
                "WHEN 'ROLE_MANAGER' THEN 'Manager' " +
                "END) AS name " +
            "FROM roles r ";

    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {

        Long roleId = rs.getLong("role_id");
        String roleName = rs.getString("name");


        return new Role(roleId, roleName);
    }

    @Override
    public Map<String, Object> mapObject(Role obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("role_id", obj.getRoleId());
        res.put("name", obj.getRoleName());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}
