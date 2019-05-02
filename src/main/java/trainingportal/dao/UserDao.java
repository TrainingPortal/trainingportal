package trainingportal.dao;


import trainingportal.dao.generic.GenericDao;
import trainingportal.model.User;
import java.util.List;
import java.util.Map;

public interface UserDao extends GenericDao<User> {

    boolean isUserExists(User login);

    List<User> findAllByRole(Long roleId);

    List<User> findAllEnabledByRole(Long roleId);

    void deleteAllByRole(Long roleId);

    User findByEmail(String email);

    User findByToken (String token);

    void updateToken (User user, String token);

    void resetToken (User user);

    void resetManagerId(Long managerId);

    void confirmRegister(User user);

    void setNewPassword(String password, String token);

    List<User> getAllByRoleAsPage(int page, int total, Long roleId);
}
