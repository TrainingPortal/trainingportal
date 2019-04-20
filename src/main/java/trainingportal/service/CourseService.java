package trainingportal.service;

import trainingportal.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourse();

    Course findCourseById(Integer Id);

    void addCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(Integer id);

}
