package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Lesson;

import java.util.List;

public interface LessonDao extends GenericDao<Lesson> {

    List<Lesson> getLessonCourseId(Long courseId);
}
