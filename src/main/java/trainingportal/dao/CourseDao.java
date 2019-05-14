package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;

import java.util.List;

public interface CourseDao extends GenericDao<Course> {

    List<CourseStatus> getStatusList();

    CourseStatus findStatusById(Long id);

    List<Course> findByTrainerId(Long id);

    int countAllByUserId(Long userId);

    List<Course> getAllAsPageById(Long id, int page, int total);

}
