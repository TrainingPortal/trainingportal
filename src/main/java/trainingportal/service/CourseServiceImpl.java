package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.CourseDao;
import trainingportal.dao.UserDao;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;
import trainingportal.model.LoggedInUser;
import trainingportal.model.Role;
import trainingportal.security.UserSecurity;
import trainingportal.security.UserSecurityImpl;

import java.util.Collection;
import java.util.List;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDAO;

    @Autowired
    private UserDao userDAO;

    @Override
    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    @Override
    public Course findById(Long CourseId) {
        return courseDAO.findById(CourseId);
    }

    @Override
    public void save(Course course) {
        courseDAO.save(course);
    }

    @Override
    public void update(Course course) {
        Course courseEdit = courseDAO.findById(course.getCourseId());
        if (courseEdit != null) {
            courseEdit.setCourseName(course.getCourseName());
            courseEdit.setCourseLevel(course.getCourseLevel());
            courseEdit.setCourseStatus(course.getCourseStatus());
            courseEdit.setMinNumber(course.getMinNumber());
            courseEdit.setMaxNumber(course.getMaxNumber());
            courseEdit.setDescription(course.getDescription());
            courseEdit.setTrainerId(course.getTrainerId());
        }
        courseDAO.update(courseEdit);
    }

    @Override
    public List<Course> getAllAsPage(int page, int total) {

        if(page == 1){
            //do nothing
        } else {
            page = (page - 1) * total + 1;
        }
        return courseDAO.getAllAsPage(page, total);
    }

    @Override
    public void deleteById(Long CourseId) {
        courseDAO.deleteById(CourseId);
    }

    @Override
    public int getNumberOfPages(List<Course> users, double total) {
        return (int) Math.ceil(users.size() / total);
    }

    @Override
    public int getPagesByUserId(Long userId, double total) {

        if(userId == Role.ADMIN){
            return (int) Math.ceil(courseDAO.countAll() / total);
        }

        return (int) Math.ceil(courseDAO.countAllByUserId(userId) / total);
    }

    @Override
    public List<CourseStatus> getStatusList() {
        return courseDAO.getStatusList();
    }

    @Override
    public CourseStatus findStatusById(Long id) {
        return courseDAO.findStatusById(id);
    }

    @Override
    public List<Course> getCoursesPage(int page, int total, Long userId, String role) {

        if(page == 1){
            //do nothing
        } else {
            page = (page - 1) * total + 1;
        }

        List<Course> courseList;

        if(role.equals("ROLE_ADMIN")){
            courseList = courseDAO.getAllAsPage(page, total);
        } else if(role.equals("ROLE_TRAINER")){
            courseList = courseDAO.getAllAsPageById(userId, page, total);
        } else {
            courseList = courseDAO.getAllAsPageByEmployeeId(userId, page, total);
        }
        for(Course course : courseList){
            course.setTrainer(userDAO.findById(course.getTrainerId()));
            course.setStatus(courseDAO.findStatusById(course.getCourseStatus()));
        }
        return courseList;
    }

    @Override
    public List<Course> findCoursesByUserId(Long id) {

        return courseDAO.findCoursesByUserId(id);
    }
}
