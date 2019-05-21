package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.UserMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.User;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl extends GenericDaoImpl<User> implements UserDao{
    //Define table and id column
    private static final String TABLE_NAME = "users";
    private static final String ID_COLUMN = "user_id";

    @Autowired
    private BaseObjectMapper<User> userBaseObjectMapper;

    @Autowired
    public UserDAOImpl(DataSource dataSource) {
        super(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    public User findUserAccount(String email) {
        String sql = UserMapper.SELECT_SQL + " where u.email = ? ";

        Object[] params = new Object[]{email};

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, params, userBaseObjectMapper);
        } else
            return null;
    }

    @Override
    public boolean isUserExists(User user) {
        return findByEmail(user.getEmail()) != null;
    }

    public User findByEmail(String email) {

        String sql = UserMapper.SELECT_SQL + "WHERE email = ?";

        List<User> users = null;
        if (this.getJdbcTemplate() != null) {
            users = this.getJdbcTemplate().query(sql,new Object[]{email},userBaseObjectMapper);
        }

        if (users != null && users.size() > 0) {
            return users.get(0);
        } else
            return null;
    }

    @Override
    public User findByToken(String token) {

        String sql = UserMapper.SELECT_SQL + "WHERE token = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql,new Object[]{token},userBaseObjectMapper);
        } else
            return null;
    }

    @Override
    public void updateToken(User user, String token) {

        String sql ="UPDATE users SET token = ? WHERE user_id = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, token, user.getUserId());
        }
    }

    @Override
    public void resetToken(User user) {

        String sql ="UPDATE users SET token = ? WHERE token = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, null, user.getToken());
        }
    }

    @Override
    public void confirmRegister(User user) {

        String sql = "UPDATE users SET enabled = ? WHERE user_id = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, user.getEnabled(), user.getUserId());
        }
    }

    @Override
    public void setNewPassword(String password, String token) {

        String sql = "UPDATE users SET password = ? WHERE token = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, password, token);
        }
    }

    @Override
    public List<User> findAllByRole(Long roleId) {

        String sql = UserMapper.SELECT_SQL + "WHERE role_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql,new Object[]{roleId},userBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public List<User> findAllByGroup(Long id) {
        String sql = "SELECT * FROM USERS u INNER JOIN USER_GROUP ug ON u.USER_ID = ug.USER_ID " +
                "WHERE ug.group_id = ?";

        return getUsers(id, sql);
    }

    @Override
    public List<User> findAllEnabledByRole(Long roleId) {
        String sql = UserMapper.SELECT_SQL + "WHERE role_id = ?  AND enabled = 1";

        return getUsers(roleId, sql);
    }

    @Override
    public void resetManagerId(Long managerId) {

        String sql = "UPDATE users SET manager_id = ? WHERE manager_id = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, null, managerId);
        }
    }

    @Override
    protected BaseObjectMapper<User> getObjectMapper() {
        return userBaseObjectMapper;
    }

    @Override
    public void deleteAllByRole(Long roleId) {
        String sql = "DELETE FROM users WHERE role_id = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, roleId);
        }
    }

    @Override
    public List<User> getAllByRoleAsPage(int page, int total, Long roleId) {

        String sql = UserMapper.SELECT_SQL +
                " WHERE role_id = ? OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return getUsers(roleId, sql);
    }

    @Override
    public int countAllByRole(Long id) {

        String sql = "SELECT COUNT(user_id) FROM users WHERE role_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, Integer.class);
        } else
            return 0;
    }

    @Override
    public List<User> searchByRole(Long id, String request, int page, int total) {

        String sql = UserMapper.SELECT_SQL + "WHERE (name LIKE '%" + request + "%' OR email LIKE '%" + request + "%') AND role_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return getUsers(id, sql);
    }

    private List<User> getUsers(Long id, String sql) {
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql,new Object[]{id},userBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public int countSearchPagesByRole(Long id, String request) {

        String sql = "SELECT COUNT(user_id) FROM users " +
                "WHERE (name LIKE '%" + request + "%' OR email LIKE '%" + request + "%') AND role_id = ? ";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, Integer.class);
        } else
            return 0;
    }

    @Override
    public List<User> getUsersByGroupIdAsPage(int page, int total, Long groupId) {

        String sql = "SELECT a.user_id, a.name, a.email, a.password, a.enabled, a.token, a.role_id, a.manager_id " +
                "FROM Users a " +
                "INNER JOIN User_Group b " +
                "ON a.user_id = b.user_id " +
                "WHERE b.group_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return getUsers(groupId, sql);
    }

    @Override
    public int countUsersByGroupId(Long groupId) {

        String sql = "SELECT COUNT(a.user_id) FROM Users a " +
                "INNER JOIN User_Group b " +
                "ON a.user_id = b.user_id " +
                "WHERE b.group_id = ? ";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{groupId}, Integer.class);
        } else
            return 0;
    }

    @Override
    public List<User> findUsersForGroupByGroupId(Long groupId) {

        String sql = "SELECT a.user_id, a.name, a.email, a.password, a.enabled, a.token, a.role_id, a.manager_id " +
                "FROM Users a " +
                "INNER JOIN Desired_Period b ON a.user_id = b.user_id " +
                "INNER JOIN Groups c ON b.course_id = c.course_id " +
                "WHERE c.id = ?";

        return getUsers(groupId, sql);
    }

    @Override
    public void deleteFromDesiredPeriodByUserId(Long userId) {

        String sql = "DELETE FROM Desired_Period WHERE user_id = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, userId);
        }
    }
}