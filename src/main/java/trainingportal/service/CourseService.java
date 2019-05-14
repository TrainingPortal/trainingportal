package trainingportal.service;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.security.core.Authentication;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface CourseService extends GenericService<Course> {

    List<CourseStatus> getStatusList();

    CourseStatus findStatusById(Long id);

    int getPagesByUserId(Long userId, double total);

    List<Course> getCoursesPage(int page, int total, Authentication auth);

    List<Course> findCoursesByUserId(Long id);
}
