package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.RoleDao;
import trainingportal.dao.SubordinateDAO;
import trainingportal.dao.UserDao;
import trainingportal.dao.UserGroupDao;
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
    @Autowired
    private SubordinateDAO subordinateDAO;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserGroupDao userGroupDao;

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    @Override
    public void confirmRegister(User user) {
        userDao.confirmRegister(user);
    }

    @Override
    public void setNewPassword(String password, String token) {
        userDao.setNewPassword(password, token);
    }

    @Override
    public List<User> findSubordinatesById(Long id) {
        return subordinateDAO.findSubordinatesById(id);
    }

    @Override
    public void updateToken(User user, String token) {
        userDao.updateToken(user, token);
    }

    @Override
    public void resetToken(User user) {
        userDao.resetToken(user);
    }

    public boolean isUserExists(User user) {
        return userDao.isUserExists(user);
    }

    @Override
    public List<User> findAllByRole(Long roleId) {
        return userDao.findAllByRole(roleId);
    }

    public List<User> findAllByGroup(Long id) {
        return userDao.findAllByGroup(id);
    }

    @Override
    public List<User> findAllEnabledByRole(Long roleId) {
        return userDao.findAllEnabledByRole(roleId);
    }

    @Override
    public void update(User user) {
        User updatedUser = userDao.findById(user.getUserId());
        if(updatedUser!=null){
            if(updatedUser.getRoleId() == Role.MANAGER && user.getRoleId() != Role.MANAGER || user.getEnabled() == 0){
                userDao.resetManagerId(updatedUser.getUserId());
            }
            updatedUser.setUserId(user.getUserId());
            updatedUser.setUserName(user.getUserName());
            updatedUser.setEmail(user.getEmail());
            //updatedUser.setPassword(user.getPassword());
            updatedUser.setEnabled(user.getEnabled());
            updatedUser.setManagerId(user.getManagerId());
            updatedUser.setRoleId(user.getRoleId());
        }
        userDao.update(updatedUser);
    }

    @Override
    public void deleteAllByRole(Long roleId) {
        userDao.deleteAllByRole(roleId);
    }

    @Override
    public User findManagerBySubordinateId(Long id) {
        return subordinateDAO.findManagerBySubordinateId(id);
    }

    @Override
    public List<User> findFreeUsers() {
        return subordinateDAO.findFreeUsers();
    }

    @Override
    public void setManagerId(Long managerId, Long userId) {
        subordinateDAO.setManagerId(managerId, userId);
    }

    @Override
    public String assignSubordinates(Long managerId, Long[] userIds) {

        if(userIds == null) {
            return "You chose nobody to add, no one is added";
        }
        for(Long userId : userIds){
            subordinateDAO.setManagerId(managerId, userId);
        }
        return "Subordinates were added: " + userIds.length + "." ;
    }

    @Override
    public List<User> getAllByRoleAsPage(int page, int rowsPerPage, Long roleId) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,rowsPerPage);

        return userDao.getAllByRoleAsPage(page, rowsPerPage, roleId);
    }

    @Override
    public List<User> getSubordinatesByIdAsPage(int page, int rowsPerPage, Long id) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,rowsPerPage);

        return subordinateDAO.getSubordinatesByIdAsPage(page, rowsPerPage, id);
    }

    @Override
    public List<User> getFreeUsersAsPage(int page, int rowsPerPage) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,rowsPerPage);

        return subordinateDAO.getFreeUsersAsPage(page, rowsPerPage);
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
        return roleDao.getRoles();
    }

    @Override
    public int getPagesByRole(Long id, double rowsPerPage) {
        return (int) Math.ceil(userDao.countAllByRole(id) / rowsPerPage);
    }

    @Override
    public int getPagesByManager(Long id, double rowsPerPage) {
        return (int) Math.ceil(subordinateDAO.countAllByManager(id) / rowsPerPage);
    }

    @Override
    public int getFreeUsersPages(double rowsPerPage) {
        return (int) Math.ceil(subordinateDAO.countFreeUsers() / rowsPerPage);
    }

    @Override
    public List<User> searchByRole(Long id, String request, int page, int rowsPerPage) {

        if(request.equals("")) {
            return Collections.emptyList();
        }

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,rowsPerPage);

        return userDao.searchByRole(id, request, page, rowsPerPage);
    }

    @Override
    public int getSearchPagesByRole(Long id, double rowsPerPage, String request) {
        return (int) Math.ceil(userDao.countSearchPagesByRole(id, request) / rowsPerPage);
    }

    @Override
    public Long getUserId(Authentication authentication) {
        return ((LoggedInUser) authentication.getPrincipal()).getId();
    }

    @Override
    public List<User> getUsersByGroupIdAsPage(int page, int rowsPerPage, Long groupId) {

        page = getPageNumber(page,rowsPerPage);

        return userDao.getUsersByGroupIdAsPage(page, rowsPerPage, groupId);
    }

    @Override
    public int getPagesAmountOfUsersByGroupId(Long groupId, double rowsPerPage) {

        return (int) Math.ceil(userDao.countUsersByGroupId(groupId) / rowsPerPage);
    }

    @Override
    public String assignUsersToGroup(Long groupId, Long[] userIds) {

        if(userIds == null) {
            return "You chose nobody to add, no one is added";
        }
        for(Long userId : userIds){
            userGroupDao.setUsersToGroup(groupId, userId);
            userDao.disableDesiredPeriodByUserId(userId);
        }
        return "Users were added: " + userIds.length + "." ;
    }

    @Override
    public List<User> findUsersForGroupByGroupId(Long groupId) {

        return userDao.findUsersForGroupByGroupId(groupId);
    }
}