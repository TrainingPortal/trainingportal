package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Homework;

import java.util.List;

public interface HomeworkDao extends GenericDao<Homework> {
    List<Homework> getHomeworkLessonId(Long homeworkId);
}
