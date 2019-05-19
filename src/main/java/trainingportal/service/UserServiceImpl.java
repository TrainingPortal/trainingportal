package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.RoleDao;
import trainingportal.dao.SubordinateDAO;
import trainingportal.dao.UserDao;
import trainingportal.model.LoggedInUser;
import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    private final SubordinateDAO subordinateRepository;

    private final UserDao userRepository;

    private final RoleDao roleRepository;

    @Autowired
    public UserServiceImpl(SubordinateDAO subordinateRepository, UserDao userRepository, RoleDao roleRepository) {
        this.subordinateRepository = subordinateRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
    public List<User> findSubordinatesById(Long id) {
        return subordinateRepository.findSubordinatesById(id);
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

    public List<User> findAllByGroup(Long id) {
        return userRepository.findAllByGroup(id);
    }

    @Override
    public List<User> findAllEnabledByRole(Long roleId) {
        return userRepository.findAllEnabledByRole(roleId);
    }

    @Override
    public void update(User user) {
        User updatedUser = userRepository.findById(user.getUserId());
        if(updatedUser!=null){
            if(updatedUser.getRoleId() == Role.MANAGER && user.getRoleId() != Role.MANAGER || user.getEnabled() == 0){
                userRepository.resetManagerId(updatedUser.getUserId());
            }
            updatedUser.setUserId(user.getUserId());
            updatedUser.setUserName(user.getUserName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEnabled(user.getEnabled());
            updatedUser.setManagerId(user.getManagerId());
            updatedUser.setRoleId(user.getRoleId());
        }
        userRepository.update(updatedUser);
    }

    @Override
    public void deleteAllByRole(Long roleId) {
        userRepository.deleteAllByRole(roleId);
    }

    @Override
    public User findManagerBySubordinateId(Long id) {
        return subordinateRepository.findManagerBySubordinateId(id);
    }

    @Override
    public List<User> findFreeUsers() {
        return subordinateRepository.findFreeUsers();
    }

    @Override
    public void setManagerId(Long managerId, Long userId) {
        subordinateRepository.setManagerId(managerId, userId);
    }

    @Override
    public String assignSubordinates(Long managerId, Long[] userIds) {

        if(userIds == null) {
            return "You chose nobody to add, no one is added";
        }
        for(Long userId : userIds){
            subordinateRepository.setManagerId(managerId, userId);
        }
        return "Subordinates were added: " + userIds.length + "." ;
    }

    @Override
    public List<User> getAllByRoleAsPage(int page, int total, Long roleId) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

        return userRepository.getAllByRoleAsPage(page, total, roleId);
    }

    @Override
    public List<User> getSubordinatesByIdAsPage(int page, int total, Long id) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

        return subordinateRepository.getSubordinatesByIdAsPage(page, total, id);
    }

    @Override
    public List<User> getFreeUsersAsPage(int page, int total) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

        return subordinateRepository.getFreeUsersAsPage(page, total);
    }

    @Override
    public Map<Long, String> setMapStatus() {

        Map<Long, String> mapStatus = new HashMap<>();
        mapStatus.put(1L, "Enabled");
        mapStatus.put(0L, "Disabled");

        return mapStatus;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.getRoles();
    }

    @Override
    public int getPagesByRole(Long id, double total) {
        return (int) Math.ceil(userRepository.countAllByRole(id) / total);
    }

    @Override
    public int getPagesByManager(Long id, double total) {
        return (int) Math.ceil(subordinateRepository.countAllByManager(id) / total);
    }

    @Override
    public int getFreeUsersPages(double total) {
        return (int) Math.ceil(subordinateRepository.countFreeUsers() / total);
    }

    @Override
    public List<User> searchByRole(Long id, String request, int page, int total) {

        if(request.equals("")) {
            return Collections.emptyList();
        }

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

        return userRepository.searchByRole(id, request, page, total);
    }

    @Override
    public int getSearchPagesByRole(Long id, double total, String request) {
        return (int) Math.ceil(userRepository.countSearchPagesByRole(id, request) / total);
    }

    @Override
    public Long getUserId(Authentication authentication) {
        return ((LoggedInUser) authentication.getPrincipal()).getId();
    }
}