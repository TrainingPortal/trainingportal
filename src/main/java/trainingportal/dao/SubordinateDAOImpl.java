package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.UserMapper;
import trainingportal.model.Role;
import trainingportal.model.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class SubordinateDAOImpl extends JdbcDaoSupport implements SubordinateDAO {
    @Autowired
    public SubordinateDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<User> findSubordinatesById(Long id) {

        String sql = UserMapper.BASE_SQL + "WHERE u.managerId = ?";

        List<User> users = this.getJdbcTemplate().query(sql,new Object[]{id}, new UserMapper());

        return users;
    }

    @Override
    public User findManagerBySubordinateId(Long id) {

        String sql = UserMapper.BASE_SQL + "WHERE u.userId = (SELECT managerId FROM users WHERE userId = ?)";

        User user = this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new UserMapper());

        return user;
    }

    @Override
    public List<User> findFreeUsers() {

        String sql = UserMapper.BASE_SQL + "WHERE u.managerId IS NULL AND u.roleId = ? AND u.enabled = 1";

        List<User> users = this.getJdbcTemplate().query(sql, new Object[]{Role.EMPLOYEE}, new UserMapper());

        return users;
    }

    @Override
    public void setManagerId(Long managerId, Long userId) {

        String sql = "UPDATE users SET managerId = ? WHERE userId = ?";

        this.getJdbcTemplate().update(sql, managerId, userId);
    }

    @Override
    public List<User> getSubordinatesByIdAsPage(int page, int total, Long id) {

        String sql = UserMapper.BASE_SQL +
                "WHERE u.managerId = ? OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        List<User> users = this.getJdbcTemplate().query(sql, new Object[]{id}, new UserMapper());

        return users;
    }

    @Override
    public List<User> getFreeUsersAsPage(int page, int total) {

        String sql = UserMapper.BASE_SQL + "WHERE u.managerId IS NULL AND u.roleId = ? AND u.enabled = 1 " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        List<User> users = this.getJdbcTemplate().query(sql, new Object[]{Role.EMPLOYEE}, new UserMapper());

        return users;
    }
}
