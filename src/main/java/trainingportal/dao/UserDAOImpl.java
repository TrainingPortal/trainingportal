package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.UserMapper;
import trainingportal.model.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl extends JdbcDaoSupport implements UserDao{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
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

    public void save(User user, Long roleId) {

        String sql = "INSERT INTO users (name, email, password, enabled, token, roleId) values (?,?,?,?,?,?)";

        this.getJdbcTemplate().update(sql, user.getUserName(),
                user.getEmail(), user.getPassword(), user.getEnabled(), user.getToken(), roleId);
    }

    @Override
    public boolean isUserExists(User user) {
        return findByEmail(user.getEmail()) != null;
    }

    public User findByEmail(String email) {

        String sql = UserMapper.BASE_SQL + "WHERE email = ?";

        List<User> users = this.getJdbcTemplate().query(sql,new Object[]{email},new UserMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

    public User findByName(String name) {

        String sql = UserMapper.BASE_SQL + "WHERE name = ?";

        List<User> users = this.getJdbcTemplate().query(sql,new Object[]{name},new UserMapper());

        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public void setDefaultRole(Long userId) {
        String sql = "insert into USER_ROLE (USER_ID, ROLE_ID) values (?,?)";

        User user = findById(userId);

        this.getJdbcTemplate().update(sql, user.getUserId(),2);
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
    public User findById(Long id) {
        String sql = UserMapper.BASE_SQL + "where userId = ?";

        User user = this.getJdbcTemplate().queryForObject(sql,new Object[]{id},new UserMapper());

        return user;
    }

    @Override
    public List<User> findAllByRole(Long roleId) {
        String sql = UserMapper.BASE_SQL + "where roleId = ?";

        List<User> users = this.getJdbcTemplate().query(sql,new Object[]{roleId},new UserMapper());

        return users;
    }

    @Override
    public void update(User user) {
        String sql = UserMapper.UPDATE_SQL + " WHERE userId = ?";

        this.getJdbcTemplate().update(sql,
                user.getUserName(),user.getEmail(),
                user.getEnabled(),user.getRoleId(),
                user.getUserId());
    }

    @Override
    public void deleteById(Long userId) {
        String sql = "DELETE FROM users WHERE userId = ?";

        this.getJdbcTemplate().update(sql, userId);
    }

    @Override
    public void deleteAllByRole(Long roleId) {
        String sql = "DELETE FROM users WHERE roleId = ?";

        this.getJdbcTemplate().update(sql, roleId);
    }
}