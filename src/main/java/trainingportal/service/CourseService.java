package trainingportal.service;


import trainingportal.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> CoursesList();
//    List<Course> getAllCoursesById(Long courseId);

    Course findCourseById(Long CourseId);

    void saveCourse(Course course);

    void editCourse(Course course);

    void deleteCourseById(Long CourseId);

}
