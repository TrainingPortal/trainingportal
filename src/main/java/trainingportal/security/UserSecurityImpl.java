package trainingportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import trainingportal.model.LoggedInUser;
import trainingportal.model.User;
import trainingportal.service.LessonService;
import trainingportal.service.UserService;

public class UserSecurityImpl implements UserSecurity {

    @Autowired
    LessonService lessonService;

    @Autowired
    UserService userService;

    public boolean hasUserId(Authentication authentication, Long userId) {

        if(((LoggedInUser)authentication.getPrincipal()).getId() == userId) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isConnectedWithTrainer(Authentication authentication, Long courseId) {

        Long userId = ((LoggedInUser)authentication.getPrincipal()).getId();

        return lessonService.isConnectedWithTrainer(userId, courseId);
    }

    @Override
    public boolean isSubordinate(Authentication authentication, Long userId) {

        User user = userService.findById(userId);

        if(((LoggedInUser)authentication.getPrincipal()).getId() == user.getManagerId()) {
            return true;
        }
        return false;
    }
}