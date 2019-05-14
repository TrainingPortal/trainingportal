package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.CourseDAOImpl;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;

@Service("courseService")
@Transactional
public class CourseServiceImpl extends GenericServiceImpl<Course> implements CourseService {

    @Autowired
    private CourseDAOImpl courseDAO;

    @Override
    public int getPages(double total) {
        return (int) Math.ceil(courseDAO.countAll() / total);
    }

    @Override
    public List<Course> findByTrainerId(Long id) {
        return courseDAO.findByTrainerId(id);
    }

    @Override
    public List<CourseStatus> getStatusList() {
        return courseDAO.getStatusList();
    }

    @Override
    public CourseStatus findStatusById(Long id) {
        return courseDAO.findStatusById(id);
    }
}
