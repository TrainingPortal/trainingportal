package trainingportal.dao;

import trainingportal.model.UserGroup;

import java.util.List;

public interface UserGroupDao {

    List<UserGroup> getUserIdByCourseId(Long courseId);

    List<UserGroup> getUserIdByLessonId(Long lessonId);

    List<UserGroup> getUserGroupListByGroupId(Long groupId);

    void setUsersToGroup(Long groupId, Long userId);

    void deleteFromUserGroupByUserIdAndGroupId(Long userId, Long groupId);
}
