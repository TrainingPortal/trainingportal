package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.UserDAOImpl;
import trainingportal.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Override
    public void setDefaultRole(Long userId){
        userRepository.setDefaultRole(userId);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public void confirmRegister(User user) {
        userRepository.confirmRegister(user);
    }

    @Override
    public void setNewPassword(String password, String token) {
        userRepository.setNewPassword(password, token);
    }

    @Override
    public void updateToken(User user, String token) {
        userRepository.updateToken(user, token);
    }

    @Override
    public void resetToken(User user) {
        userRepository.resetToken(user);
    }

    public boolean isUserExists(User user) {
        return userRepository.isUserExists(user);
    }


}