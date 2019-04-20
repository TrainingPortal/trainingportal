package trainingportal.service;

import trainingportal.model.User;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    void saveUser(User user);

    boolean isUserExists(User user);

    void setDefaultRole(Long userId);

    User findByToken(String token);

    void confirmRegister(User user);

    void setNewPassword(String password, String token);

    void updateToken (User user, String token);

    void resetToken (User user);
}
