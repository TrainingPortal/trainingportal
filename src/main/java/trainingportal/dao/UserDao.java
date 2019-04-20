package trainingportal.dao;

import trainingportal.model.User;

public interface UserDao {

    void save(User user);

    boolean isUserExists(User login);

    User findById(Long id);

    User findByEmail(String email);

    User findByName(String name);

    void setDefaultRole(Long userId);

    User findByToken (String token);

    void updateToken (User user, String token);

    void resetToken (User user);

    void confirmRegister(User user);

    void setNewPassword(String password, String token);
}
