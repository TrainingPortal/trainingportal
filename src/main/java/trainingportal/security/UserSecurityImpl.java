package trainingportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.model.Role;
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

    @Autowired
    private GroupService groupService;

    public boolean hasUserId(Long userId) {

        return getLoggedInUserId().equals(userId);
    }

    @Override
    public boolean isConnectedWithTrainerByCourseId(Long courseId) {

        return lessonService.isConnectedWithTrainerByCourseId(getLoggedInUserId(), courseId);
    }

    @Override
    public boolean isSubordinate(Long userId) {

        User user = userService.findById(userId);

        return getLoggedInUserId().equals(user.getManagerId());
    }

    @Override
    public boolean isTrainer(Long userId) {

        User user = userService.findById(userId);

        return user.getRoleId().equals(Role.TRAINER);
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
    public boolean isConnectedWithTrainerByGroupId(Long groupId) {

        return groupService.isConnectedWithTrainerByGroupId(getLoggedInUserId(), groupId);
    }

    @Override
    public boolean isConnectedWithUserByGroupId(Long groupId) {

        return groupService.isConnectedWithUserByGroupId(getLoggedInUserId(), groupId);
    }

    @Override
    public boolean isConnectedWithLessonByLessonId(Long lessonId) {

        return lessonService.isConnectedWithLessonByLessonId(getLoggedInUserId(), lessonId);
    }
}