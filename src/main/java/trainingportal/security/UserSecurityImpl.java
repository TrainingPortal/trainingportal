package trainingportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.model.User;
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
    public boolean isConnectedWithTrainerByCourseId(Long courseId) {

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

    @Override
    public boolean isConnectedWithTrainerByLessonId(Long lessonId) {

        return lessonService.isConnectedWithTrainerByLessonId(getLoggedInUserId(), lessonId);
    }

    @Override
    public boolean isConnectedWithLessonByLessonId(Long lessonId) {

        return lessonService.isConnectedWithLessonByLessonId(getLoggedInUserId(), lessonId);
    }
}