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
import trainingportal.service.generic.GenericServiceImpl;
import trainingportal.model.LoggedInUser;
import trainingportal.model.Role;
import trainingportal.security.UserSecurity;
import trainingportal.security.UserSecurityImpl;


import java.util.Collection;
import java.util.List;

@Service("courseService")
@Transactional
public class CourseServiceImpl extends GenericServiceImpl<Course> implements CourseService {
    @Autowired
    private CourseDao courseDAO;
    @Autowired
    private UserDao userDAO;

    @Override
    public int getPages(double total) {
        return (int) Math.ceil(courseDAO.countAll() / total);
    }

    @Override
    public List<Course> findByTrainerId(Long id) {
        return courseDAO.findByTrainerId(id);
    }

    public int getPagesByUserId(Long userId, double total) {

        if(userId.equals(Role.ADMIN)){
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

        //get page number  GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

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
