package trainingportal.dao;

import trainingportal.model.Course;

import java.util.List;

public interface CourseDAO {

    List<Course> CoursesList();

    Course findCourseById(Long courseId);

    void saveCourse(Course course);

    void editCourse(Course course);

    void deleteCourseById(Long courseId);

}
