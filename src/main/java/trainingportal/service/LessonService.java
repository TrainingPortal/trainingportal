package trainingportal.service;

import trainingportal.model.Lesson;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface LessonService extends GenericService<Lesson> {
    List<Lesson> getLessonCourseId(Long lessonId);

    List<Lesson> getLessonsPageByCourseId(int page, int total, Long courseId);

    int getPages(Long courseId, double total);

    boolean isConnectedWithTrainer(Long userId, Long courseId);

    Lesson getLessonByScheduleId(Long id);

    boolean isConnectedWithLessonByCourseId(Long userId, Long courseId);
}
