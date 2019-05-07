package trainingportal.service;

import trainingportal.model.Task;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface TaskService extends GenericService<Task> {
    List<Task> getTaskLessonById(Long homeworkId);
}

