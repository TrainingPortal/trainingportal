package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.model.Role;
import trainingportal.model.User;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserService userService;

    @Override
    public User initManager(User user) {

        if(user.getRoleId().intValue() == Role.EMPLOYEE && user.getManagerId() != 0){
            return userService.findManagerBySubordinateId(user.getUserId());
        } else {
            return new User();
        }
    }

    @Override
    public String getReadableRole(User user) {

        String role;
        Long roleId = user.getRoleId();

        if (roleId.equals(Role.ADMIN)) {
            role = "Admin";
        } else if (roleId.equals(Role.MANAGER)) {
            role = "Manager";
        } else if (roleId.equals(Role.TRAINER)) {
            role = "Trainer";
        } else {
            role = "Student";
        }
        return role;
    }
}
