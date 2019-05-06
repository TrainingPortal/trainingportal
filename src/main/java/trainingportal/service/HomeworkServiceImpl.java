package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.HomeworkDaoImpl;
import trainingportal.model.Homework;

import java.util.List;

@Service("HomeworkService")
@Transactional
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkDaoImpl homeworkDao;

    @Override
    public List<Homework> findAll() {
        return homeworkDao.findAll();
    }

    @Override
    public Homework findById(Long homeworkId) {
        return homeworkDao.findById(homeworkId);
    }

    @Override
    public void save(Homework homework) {
        homeworkDao.save(homework);
    }

    @Override
    public void update(Homework homework) {
        Homework homeworkEdit = homeworkDao.findById(homework.getHomeworkId());
        if (homeworkEdit != null) {
            homeworkEdit.setHomeworkName(homework.getHomeworkName());
            homeworkEdit.setHomeworkDeadlineDate(homework.getHomeworkDeadlineDate());

        }
        homeworkDao.update(homeworkEdit);
    }


    @Override
    public void deleteById(Long homeworkId) {
        homeworkDao.deleteById(homeworkId);
    }

    @Override
    public List<Homework> getAllAsPage(int page, int total) {
        return null;
    }

    @Override
    public int getNumberOfPages(List<Homework> users, double total) {
        return 0;
    }

    @Override
    public List<Homework> getHomeworkLessonId(Long homeworkId) {
        return homeworkDao.getHomeworkLessonId(homeworkId);
    }
}


