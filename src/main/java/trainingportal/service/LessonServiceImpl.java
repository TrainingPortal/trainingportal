package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.LessonDao;
import trainingportal.dao.UserGroupDao;
import trainingportal.model.Lesson;
import trainingportal.model.User;
import trainingportal.model.UserGroup;

import java.util.List;

@Service("lessonService")
@Transactional
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private UserGroupDao userGroupDao;

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
            lessonEdit.setLessonDescription(lesson.getLessonDescription());
            lessonEdit.setLessonDuration(lesson.getLessonDuration());
            lessonEdit.setCourseId(lesson.getCourseId());
            lessonEdit.setLessonNumber(lesson.getLessonNumber());
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


    @Override
    public List<Lesson> getLessonCourseId(Long courseId) {
        return lessonDao.getLessonCourseId(courseId);
    }

    @Override
    public List<Lesson> getLessonsPageByCourseId(int page, int total, Long courseId) {

        if(page == 1){
            //do nothing
        } else {
            page = (page - 1) * total + 1;
        }
        return lessonDao.getAllAsPageByCourseId(courseId, page, total);
    }

    @Override
    public int getPages(Long courseId, double total) {
        return (int) Math.ceil(lessonDao.countAllByCourseId(courseId) / total);
    }

    @Override
    public boolean isConnectedWithTrainer(Long userId, Long courseId) {
        Long trainerId =  lessonDao.getTrainerIdByCourseId(courseId);

        return userId == trainerId;
    }

    @Override
    public boolean isConnectedWithLessonByCourseId(Long userId, Long courseId) {

        List<UserGroup> users =  userGroupDao.getUserIdByCourseId(courseId);

        for(UserGroup user : users){
            if(user.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }
}
