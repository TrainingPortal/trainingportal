package trainingportal.dao;

import org.apache.poi.ss.formula.functions.T;
import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Course;

import java.util.List;

public interface CourseDao extends GenericDao<Course> {

    List<Course> getAllAsPage(int page, int total);
}
