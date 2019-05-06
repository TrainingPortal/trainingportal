package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Task;

import java.util.List;

public interface TaskDao extends GenericDao<Task> {
    List<Task> getTaskLessonById(Long homeworkId);
}
