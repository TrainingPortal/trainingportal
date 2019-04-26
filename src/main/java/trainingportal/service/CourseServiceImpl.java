package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.CourseDAOImpl;
import trainingportal.model.Course;

import java.util.List;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAOImpl courseDAO;

    @Override
    public List<Course> CoursesList() {
        return courseDAO.CoursesList();
    }

    @Override
    public Course findCourseById(Long CourseId) {
        return courseDAO.findCourseById(CourseId);
    }

    @Override
    public void saveCourse(Course course) {
        courseDAO.saveCourse(course);
    }

    @Override
    public void editCourse(Course course) {
        Course courseEdit = courseDAO.findCourseById(course.getCourseId());
        if (courseEdit != null) {
            courseEdit.setCourseName(course.getCourseName());
            courseEdit.setCourseLevel(course.getCourseLevel());
            courseEdit.setCourseStatus(course.getCourseStatus());
            courseEdit.setMinNumber(course.getMinNumber());
            courseEdit.setMaxNumber(course.getMaxNumber());
            courseEdit.setDescription(course.getDescription());
            courseEdit.setTrainerId(course.getTrainerId());
            courseEdit.setLessonNumber(course.getLessonNumber());
        }
        courseDAO.editCourse(courseEdit);
    }

    @Override
    public void deleteCourseById(Long CourseId) {
        courseDAO.deleteCourseById(CourseId);
    }
}
