package trainingportal.dao;

import trainingportal.model.Course;

import java.util.List;

public interface CourseDao {

    List<Course> getAllCourse();

    Course findCourseById(Integer Id);

    void addCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(Integer Id);
}
