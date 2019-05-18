package trainingportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import trainingportal.model.User;
import trainingportal.service.GroupService;
import trainingportal.service.LessonService;
import trainingportal.service.UserService;

@Service
public class UserSecurityImpl implements UserSecurity {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private UserService userService;

    public boolean hasUserId(Long userId) {

        return getLoggedInUserId() == userId;
    }

    @Override
    public boolean isConnectedWithTrainer(Long courseId) {

        return lessonService.isConnectedWithTrainer(getLoggedInUserId(), courseId);
    }

    @Override
    public boolean isSubordinate(Long userId) {

        User user = userService.findById(userId);

        return getLoggedInUserId() == user.getManagerId();
    }

    @Override
    public boolean isConnectedWithLessonByCourseId(Long courseId) {

        return lessonService.isConnectedWithLessonByCourseId(getLoggedInUserId(), courseId);
    }
}