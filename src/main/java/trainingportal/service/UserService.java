package trainingportal.service;

import trainingportal.model.User;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    void saveUser(User user);

    boolean isUserExists(User user);

    void setDefaultRole(Long userId);


}
