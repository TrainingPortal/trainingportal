package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.UserDAOImpl;
import trainingportal.model.User;

import java.util.List;

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
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void save(User user, Long roleId) {
        userRepository.save(user, roleId);
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

    @Override
    public List<User> findAllByRole(Long roleId) {
        return userRepository.findAllByRole(roleId);
    }

    @Override
    public void update(User user) {
        User updatedUser = userRepository.findById(user.getUserId());
        if(updatedUser!=null){
            updatedUser.setUserId(user.getUserId());
            updatedUser.setUserName(user.getUserName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEnabled(user.getEnabled());
            updatedUser.setRoleId(user.getRoleId());
        }
        userRepository.update(updatedUser);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllByRole(Long roleId) {
        userRepository.deleteAllByRole(roleId);
    }
}