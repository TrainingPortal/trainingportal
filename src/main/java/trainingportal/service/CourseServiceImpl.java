package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.CourseDao;
import trainingportal.dao.UserDao;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;
import trainingportal.model.Role;
import trainingportal.model.User;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;

@Service("courseService")
@Transactional
public class CourseServiceImpl extends GenericServiceImpl<Course> implements CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private UserDao userDao;

    @Override
    public int getPages(double total) {
        return (int) Math.ceil(courseDao.countAll() / total);
    }

    @Override
    public List<Course> findByTrainerId(Long id) {
        return courseDao.findByTrainerId(id);
    }

    public int getPagesByUserId(Long userId, double total) {

        if (userId.equals(Role.ADMIN)) {
            return (int) Math.ceil(courseDao.countAll() / total);
        }

        return (int) Math.ceil(courseDao.countAllByUserId(userId) / total);
    }

    @Override
    public List<CourseStatus> getStatusList() {
        return courseDao.getStatusList();
    }

    @Override
    public CourseStatus findStatusById(Long id) {
        return courseDao.findStatusById(id);
    }

    @Override
    public List<Course> getCoursesPage(int page, int total, Long userId, String role) {

        //get page number  GENERIC SERVICE implementation class
        page = getPageNumber(page, total);

        List<Course> courseList;

        if (role.equals("ROLE_ADMIN")) {
            courseList = courseDao.getAllAsPage(page, total);
        } else if (role.equals("ROLE_TRAINER")) {
            courseList = courseDao.getAllAsPageById(userId, page, total);
        } else {
            courseList = courseDao.getAllAsPageByEmployeeId(userId, page, total);
        }
        for (Course course : courseList) {
            course.setTrainer(userDao.findById(course.getTrainerId()));
            course.setStatus(courseDao.findStatusById(course.getCourseStatus()));
        }
        return courseList;
    }

    @Override
    public List<Course> findCoursesByUser(User user) {

        List<Course> courseList;

        if(user.getRoleId().equals(Role.TRAINER)) {
            courseList =  courseDao.findByTrainerId(user.getUserId());
        } else {
            courseList = courseDao.findCoursesByUserId(user.getUserId());

            for(Course course : courseList){
                course.setTrainer(userDao.findById(course.getTrainerId()));
            }
        }
        return courseList;
    }


}
