package trainingportal.dao;

import trainingportal.model.Course;

import java.util.List;

public interface CourseDao {

    List<Course> getCoursAll();

    Course findCourseById(Long id);

    void addCourse(Course course);

    void updateCourseById(Long id);


    void deleteCourseById(Long Id);

    void saveCourse(Course course, Long Id);

}
