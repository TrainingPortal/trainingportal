package trainingportal.dao;

import trainingportal.model.User;

public interface UserDao {

    void save(User user);

    boolean isUserExists(User login);

    User findById(Long id);

    User findByEmail(String email);

    User findByName(String name);

    void setDefaultRole(Long userId);

}
