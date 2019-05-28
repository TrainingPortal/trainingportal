package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;

import java.util.List;

public interface CourseDao extends GenericDao<Course> {

    List<CourseStatus> getStatusList();

    CourseStatus findStatusById(Long id);

    List<Course> findByTrainerId(Long id);

    int countAllByTrainerId(Long trainerId);

    int countAllByUserId(Long userId);

    List<Course> getAllAsPageById(Long id, int page, int rowsPerPage);

    List<Course> findCoursesByUserId(Long id);

    List<Course> getAllAsPageByEmployeeId(Long userId, int page, int rowsPerPage);

    Course findCourseIdByGroupId(Long groupId);
}
