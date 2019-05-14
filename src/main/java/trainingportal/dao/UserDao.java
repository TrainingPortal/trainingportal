package trainingportal.dao;


import trainingportal.dao.generic.GenericDao;
import trainingportal.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    boolean isUserExists(User login);

    List<User> findAllByRole(Long roleId);

    List<User> findAllByGroup(Long id);

    List<User> findAllEnabledByRole(Long roleId);

    void deleteAllByRole(Long roleId);

    User findByEmail(String email);

    User findByToken(String token);

    void updateToken (User user, String token);

    void resetToken (User user);

    void resetManagerId(Long managerId);

    void confirmRegister(User user);

    void setNewPassword(String password, String token);

    List<User> getAllByRoleAsPage(int page, int total, Long roleId);

    int countAllByRole(Long id);

    List<User> searchByRole(Long id, String request, int page, int total);

    int countSearchPagesByRole(Long id, String request);
}
