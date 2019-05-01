package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.LessonDaoImpl;
import trainingportal.model.Lesson;

import java.util.List;

@Service("lessonService")
@Transactional
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDaoImpl lessonDao;

    @Override
    public List<Lesson> findAll() {
        return lessonDao.findAll();
    }

    @Override
    public Lesson findById(Long LessonId) {
        return lessonDao.findById(LessonId);
    }

    @Override
    public void save(Lesson lesson) {
        lessonDao.save(lesson);
    }

    @Override
    public void update(Lesson lesson) {
        Lesson lessonEdit = lessonDao.findById(lesson.getLessonId());
        if (lessonEdit != null) {
            lessonEdit.setLessonName(lesson.getLessonName());
            lessonEdit.setLessonDuration(lesson.getLessonDuration());
            lessonEdit.setLessonDescription(lesson.getLessonDescription());
            lessonEdit.setLessonDate(lesson.getLessonDate());
            lessonEdit.setHomeworkId(lesson.getHomeworkId());
            lessonEdit.setGroupId(lesson.getGroupId());
        }
        lessonDao.update(lessonEdit);
    }


    @Override
    public void deleteById(Long LessonId) {
        lessonDao.deleteById(LessonId);
    }


    @Override
    public List<Lesson> getAllAsPage(int page, int total) {
        return null;
    }

    @Override
    public int getNumberOfPages(List<Lesson> users, double total) {
        return 0;
    }

}
