package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.TaskDaoImpl;
import trainingportal.model.Task;

import java.util.List;


@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskDaoImpl taskDao;

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public Task findById(Long taskId) {
        return taskDao.findById(taskId);
    }

    @Override
    public void save(Task task) {
        taskDao.save(task);
    }

    @Override
    public void update(Task task) {
        Task taskEdit = taskDao.findById(task.getTaskId());
        if (taskEdit != null) {
            taskEdit.setHomeworkId(task.getHomeworkId());
            taskEdit.setTaskDescription(task.getTaskDescription());

        }
        taskDao.update(taskEdit);
    }

    @Override
    public void deleteById(Long taskId) {
        taskDao.deleteById(taskId);
    }

    @Override
    public List<Task> getAllAsPage(int page, int total) {
        return null;
    }

    @Override
    public int getNumberOfPages(List<Task> users, double total) {
        return 0;
    }

    @Override
    public List<Task> getTaskLessonById(Long homeworkId) {
        return taskDao.getTaskLessonById(homeworkId);
    }
}


