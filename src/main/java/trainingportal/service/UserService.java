package trainingportal.service;

import trainingportal.model.User;

import java.util.List;

public interface UserService {

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

    List<User> findSubordinatesById(Long id);

    User findManagerBySubordinateId(Long id);

    List<User> findFreeUsers();

    void setManagerId(Long managerId, Long userIds);

    String assignSubordinates(Long managerId, Long[] userIds);

    List<User> getAllByRoleAsPage(int page, int total, Long roleId);

    List<User> getSubordinatesByIdAsPage(int page, int total, Long id);

    List<User> getFreeUsersAsPage(int page, int total);

    int getNumberOfPages(List<User> users, double total);
}
