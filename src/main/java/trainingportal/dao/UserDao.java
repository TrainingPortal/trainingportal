package trainingportal.dao;

import empexcel.model.Emp;
import trainingportal.model.User;

import java.util.List;

public interface UserDao {

    void save(User user, Long roleId);

    boolean isUserExists(User login);

    List<User> findAllByRole(Long roleId);

    void update(User user);

    void deleteById(Long userId);

    void deleteAllByRole(Long roleId);

    User findById(Long id);

    User findByEmail(String email);

    User findByName(String name);

    void setDefaultRole(Long userId);

    User findByToken (String token);

    void updateToken (User user, String token);

    void resetToken (User user);

    void confirmRegister(User user);

    void setNewPassword(String password, String token);

    List<User> getAllByRoleAsPage(int page, int total, Long roleId);
}
