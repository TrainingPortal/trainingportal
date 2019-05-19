package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.TaskDao;
import trainingportal.model.Task;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;


@Service("taskService")
@Transactional
public class TaskServiceImpl extends GenericServiceImpl<Task> implements TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
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
    public List<Task> getTaskLessonById(Long homeworkId) {
        return taskDao.getTaskLessonById(homeworkId);
    }
}


