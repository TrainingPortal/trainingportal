package trainingportal.service;

import trainingportal.model.Lesson;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface LessonService extends GenericService<Lesson> {
    List<Lesson> getLessonCourseId(Long lessonId);
}
