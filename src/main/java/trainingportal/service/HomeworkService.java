package trainingportal.service;

import trainingportal.model.Homework;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface HomeworkService extends GenericService<Homework> {
    List<Homework> getHomeworkLessonId(Long homeworkId);
}

