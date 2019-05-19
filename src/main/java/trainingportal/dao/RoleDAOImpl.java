package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDao;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.RoleMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Role;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class RoleDAOImpl extends GenericDaoImpl<Role> implements RoleDao {

    private final BaseObjectMapper<Role> roleBaseObjectMapper;

    @Autowired
    public RoleDAOImpl(DataSource dataSource, BaseObjectMapper<Role> roleBaseObjectMapper) {
        this.setDataSource(dataSource);
        this.roleBaseObjectMapper = roleBaseObjectMapper;
    }

    @Override
    protected BaseObjectMapper<Role> getObjectMapper() {
        return roleBaseObjectMapper;
    }

    public List<String> getRoleNames(Long userId) {
        String sql = "SELECT r.name FROM users u" //
                + " INNER JOIN roles r " //
                + "ON u.role_id = r.role_id AND u.user_id = ? ";

        Object[] params = new Object[]{userId};

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForList(sql, params, String.class);
        } else
            return Collections.emptyList();
    }

    public List<Role> getRoles() {
        List<Role> roles;
        if (this.getJdbcTemplate() != null) {
            roles = this.getJdbcTemplate().query(RoleMapper.SELECT_SQL, roleBaseObjectMapper);
            roles.remove(0); //Remove ROLE_ADMIN from the list
            return roles;
        } else
            return Collections.emptyList();
    }
}
