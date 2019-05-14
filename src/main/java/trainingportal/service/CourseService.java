package trainingportal.service;

import trainingportal.model.Course;
import trainingportal.model.CourseStatus;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface CourseService extends GenericService<Course> {

    List<CourseStatus> getStatusList();

    CourseStatus findStatusById(Long id);

    int getPages(double total);

    List<Course> findByTrainerId(Long id);
}
