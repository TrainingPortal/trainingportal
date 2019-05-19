package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.UserMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Role;
import trainingportal.model.User;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class SubordinateDAOImpl extends JdbcDaoSupport implements SubordinateDAO {
    private final BaseObjectMapper<User> userBaseObjectMapper;

    @Autowired
    public SubordinateDAOImpl(DataSource dataSource, BaseObjectMapper<User> userBaseObjectMapper) {
        this.setDataSource(dataSource);
        this.userBaseObjectMapper = userBaseObjectMapper;
    }

    @Override
    public List<User> findSubordinatesById(Long id) {

        String sql = UserMapper.SELECT_SQL + "WHERE u.manager_id = ?";

        List<User> users;
        if (this.getJdbcTemplate() != null) {
            users = this.getJdbcTemplate().query(sql,new Object[]{id}, userBaseObjectMapper);
            return users;
        } else
            return Collections.emptyList();
    }

    @Override
    public User findManagerBySubordinateId(Long id) {

        String sql = UserMapper.SELECT_SQL + "WHERE u.user_id = (SELECT manager_id FROM users WHERE user_id = ?)";

        User user = null;
        if (this.getJdbcTemplate() != null) {
            user = this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, userBaseObjectMapper);
        }

        return user;
    }

    @Override
    public List<User> findFreeUsers() {

        String sql = UserMapper.SELECT_SQL + "WHERE u.manager_id IS NULL AND u.role_id = ? AND u.enabled = 1";

        return getSubordinates(sql);
    }

    @Override
    public void setManagerId(Long managerId, Long userId) {

        String sql = "UPDATE users SET manager_id = ? WHERE user_id = ?";

        this.getJdbcTemplate().update(sql, managerId, userId);
    }

    @Override
    public List<User> getSubordinatesByIdAsPage(int page, int total, Long id) {

        String sql = UserMapper.SELECT_SQL +
                "WHERE u.manager_id = ? OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        List<User> users;
        if (this.getJdbcTemplate() != null) {
            users = this.getJdbcTemplate().query(sql, new Object[]{id}, userBaseObjectMapper);
            return users;
        } else
            return Collections.emptyList();
    }

    @Override
    public List<User> getFreeUsersAsPage(int page, int total) {

        String sql = UserMapper.SELECT_SQL + "WHERE u.manager_id IS NULL AND u.role_id = ? AND u.enabled = 1 " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return getSubordinates(sql);
    }

    private List<User> getSubordinates(String sql) {
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{Role.EMPLOYEE}, userBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public int countAllByManager(Long id) {

        String sql = "SELECT COUNT(user_id) FROM users WHERE manager_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, Integer.class);
        } else
            return 0;
    }

    @Override
    public int countFreeUsers() {

        String sql = "SELECT COUNT(user_id) FROM users WHERE manager_id IS NULL AND role_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{Role.EMPLOYEE}, Integer.class);
        } else
            return 0;
    }
}
