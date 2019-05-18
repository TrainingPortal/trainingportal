package trainingportal.dao;

import trainingportal.model.UserGroup;

import java.util.List;

public interface UserGroupDao {

    List<UserGroup> getUserIdByCourseId(Long courseId);
}
