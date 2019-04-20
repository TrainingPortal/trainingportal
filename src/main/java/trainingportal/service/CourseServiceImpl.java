package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.dao.CourseDAOImpl;
import trainingportal.model.Course;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAOImpl courseRep;

    @Override
    public List<Course> getAllCourse() {
        return courseRep.getAllCourse();
    }

    @Override
    public Course findCourseById(Integer Id) {
        return courseRep.findCourseById(Id);
    }

    @Override
    public void addCourse(Course course) {
        courseRep.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseRep.updateCourse(course);
    }

    @Override
    public void deleteCourse(Integer Id) {
        courseRep.deleteCourse(Id);
    }

}
