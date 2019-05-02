package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.RoleMapper;
import trainingportal.mapper.UserMapper;
import trainingportal.model.Role;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class RoleDAOImpl extends JdbcDaoSupport {
 
    @Autowired
    public RoleDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<String> getRoleNames(Long userId) {
        String sql = "SELECT r.name FROM users u" //
                + " INNER JOIN roles r " //
                + "ON u.roleid = r.roleid AND u.userId = ? ";

        Object[] params = new Object[] { userId };

        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }

    public List<Role> getRoles() {

        List<Role> roles = this.getJdbcTemplate().query(RoleMapper.SELECT_SQL, new RoleMapper());

        roles.remove(0); //Remove ROLE_ADMIN from the list

        return roles;
    }
}