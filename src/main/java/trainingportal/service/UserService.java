package trainingportal.service;

import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.service.generic.GenericService;

import java.util.List;
import java.util.Map;

public interface UserService extends GenericService<User> {

    boolean isUserExists(User login);

    List<User> findAllByRole(Long roleId);

    List<User> findAllEnabledByRole(Long roleId);

    void deleteAllByRole(Long roleId);

    User findByEmail(String email);

    User findByToken(String token);

    void updateToken (User user, String token);

    void resetToken (User user);

    void confirmRegister(User user);

    void setNewPassword(String password, String token);

    List<User> findSubordinatesById(Long id);

    User findManagerBySubordinateId(Long id);

    List<User> findFreeUsers();

    void setManagerId(Long managerId, Long userIds);

    String assignSubordinates(Long managerId, Long[] userIds);

    List<User> getAllByRoleAsPage(int page, int total, Long roleId);

    List<User> getSubordinatesByIdAsPage(int page, int total, Long id);

    Map<Long, String> setMapStatus();

    List<User> getFreeUsersAsPage(int page, int total);

    List<Role> getRoles();
}
