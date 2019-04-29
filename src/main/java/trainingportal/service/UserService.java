package trainingportal.service;

import trainingportal.model.User;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface UserService extends GenericService<User> {

    boolean isUserExists(User login);

    List<User> findAllByRole(Long roleId);

    void deleteAllByRole(Long roleId);

    User findByEmail(String email);

    User findByName(String name);

    User findByToken (String token);

    void updateToken (User user, String token);

    void resetToken (User user);

    void confirmRegister(User user);

    void setNewPassword(String password, String token);

    List<User> findSubordinatesById(Long id);

    User findManagerBySubordinateId(Long id);

    List<User> findFreeUsers();

    void setManagerId(Long managerId, Long userIds);

    String assignSubordinates(Long managerId, Long[] userIds);
}
