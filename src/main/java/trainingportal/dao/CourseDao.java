package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;

import java.util.List;

public interface CourseDao extends GenericDao<Course> {

    List<Course> getAllAsPage(int page, int total);

    List<CourseStatus> getStatusList();

    CourseStatus findStatusById(Long id);
}
