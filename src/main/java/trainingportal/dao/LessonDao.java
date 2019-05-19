package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Lesson;

import java.util.List;

public interface LessonDao extends GenericDao<Lesson> {

    List<Lesson> getLessonCourseId(Long courseId);

    List<Lesson> getAllAsPageByCourseId(Long courseId, int page, int total);

    List<Lesson> getAllAsPageByTrainerId(Long userId, Long courseId, int page, int total);

    int countAllByCourseId(Long courseId);

    Long getTrainerIdByCourseId(Long id);

    Lesson getLessonByScheduleId(Long id);
  
    Long getTrainerIdByLessonId(Long lessonId);
}
