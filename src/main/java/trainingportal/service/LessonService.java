package trainingportal.service;

import trainingportal.model.Lesson;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface LessonService extends GenericService<Lesson> {
    List<Lesson> getLessonCourseId(Long lessonId);

    List<Lesson> getLessonsPageByCourseId(int page, int rowsPerPage, Long courseId);

    int getPages(Long courseId, double rowsPerPage);

    boolean isConnectedWithTrainerByCourseId(Long userId, Long courseId);

    Lesson getLessonByScheduleId(Long id);

    boolean isConnectedWithLessonByCourseId(Long userId, Long courseId);

    boolean isConnectedWithTrainerByLessonId(Long userId, Long lessonId);

    boolean isConnectedWithLessonByLessonId(Long userId, Long lessonId);
}
