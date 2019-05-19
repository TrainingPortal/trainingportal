package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.HomeworkDao;
import trainingportal.model.Homework;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;

@Service("HomeworkService")
@Transactional
public class HomeworkServiceImpl extends GenericServiceImpl<Homework> implements HomeworkService {

    private final HomeworkDao homeworkDao;

    @Autowired
    public HomeworkServiceImpl(HomeworkDao homeworkDao) {
        this.homeworkDao = homeworkDao;
    }

    @Override
    public void update(Homework homework) {
        Homework homeworkEdit = homeworkDao.findById(homework.getHomeworkId());
        if (homeworkEdit != null) {
            homeworkEdit.setLessonId(homework.getLessonId());
            homeworkEdit.setHomeworkName(homework.getHomeworkName());
            homeworkEdit.setHomeworkDeadlineDate(homework.getHomeworkDeadlineDate());

        }
        homeworkDao.update(homeworkEdit);
    }

    @Override
    public List<Homework> getHomeworkLessonId(Long homeworkId) {
        return homeworkDao.getHomeworkLessonId(homeworkId);
    }
}


