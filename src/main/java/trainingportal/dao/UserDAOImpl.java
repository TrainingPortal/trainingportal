package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.UserMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.User;
import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl extends GenericDaoImpl<User> implements UserDao{
    @Autowired
    PasswordEncoder passwordEncoder;
    //Define table and id column
    private static final String TABLE_NAME = "users";
    private static final String ID_COLUMN = "userId";

    public UserDAOImpl(DataSource dataSource) {
        super(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    public User findUserAccount(String email) {

        String sql = UserMapper.BASE_SQL + " where u.email = ? ";
 
        Object[] params = new Object[] { email };
        UserMapper mapper = new UserMapper();
        try {
            User userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean isUserExists(User user) {
        return findByEmail(user.getEmail())==null ? false : true;
    }

    public User findByEmail(String email) {

        String sql = UserMapper.BASE_SQL + "WHERE email = ?";

        List<User> users = this.getJdbcTemplate().query(sql,new Object[]{email},new UserMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public User findByToken(String token) {

        String sql = UserMapper.BASE_SQL + "WHERE Token = ?";

        User user = this.getJdbcTemplate().queryForObject(sql,new Object[]{token},new UserMapper());

        return user;
    }

    @Override
    public void updateToken(User user, String token) {

        String sql ="UPDATE users SET token = ? WHERE userId = ?";

        this.getJdbcTemplate().update(sql, token, user.getUserId());
    }

    @Override
    public void resetToken(User user) {

        String sql ="UPDATE users SET token = ? WHERE token = ?";

        this.getJdbcTemplate().update(sql, null, user.getToken());
    }

    @Override
    public void confirmRegister(User user) {

        String sql = "UPDATE users SET enabled = ? WHERE userId = ?";

        this.getJdbcTemplate().update(sql, user.getEnabled(), user.getUserId());
    }

    @Override
    public void setNewPassword(String password, String token) {

        String sql = "UPDATE users SET password = ? WHERE token = ?";

        this.getJdbcTemplate().update(sql, password, token);
    }

    @Override
    public List<User> findAllByRole(Long roleId) {

        String sql = UserMapper.BASE_SQL + "WHERE roleId = ?";

        List<User> users = this.getJdbcTemplate().query(sql,new Object[]{roleId},new UserMapper());

        return users;
    }

    @Override
    public List<User> findAllEnabledByRole(Long roleId) {
        String sql = UserMapper.BASE_SQL + "WHERE roleId = ?  AND enabled = 1";

        List<User> users = this.getJdbcTemplate().query(sql,new Object[]{roleId},new UserMapper());

        return users;
    }

    @Override
    public void resetManagerId(Long managerId) {

        String sql = "UPDATE users SET managerId = ? WHERE managerId = ?";

        this.getJdbcTemplate().update(sql, null, managerId);
    }

    @Override
    protected BaseObjectMapper<User> getObjectMapper() {
        return new UserMapper();
    }

    @Override
    public void deleteAllByRole(Long roleId) {
        String sql = "DELETE FROM users WHERE roleId = ?";

        this.getJdbcTemplate().update(sql, roleId);
    }

    @Override
    public List<User> getAllByRoleAsPage(int page, int total, Long roleId) {

        String sql = UserMapper.BASE_SQL +
                " WHERE roleId = ? OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        List<User> users = this.getJdbcTemplate().query(sql, new Object[]{roleId}, new UserMapper());

        return users;
    }
}