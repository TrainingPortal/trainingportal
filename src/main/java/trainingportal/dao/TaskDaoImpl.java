package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.TaskMapper;
import trainingportal.model.Task;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional

public class TaskDaoImpl extends JdbcDaoSupport implements TaskDao {


    @Autowired
    public TaskDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    //old realisation, work but need to rework(need to understand how to put in service)
    @Override
    public List<Task> findAll() {
        String sql = TaskMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new TaskMapper());
    }

    @Override
    public Task findById(Long taskId) {
        String sql = TaskMapper.SELECT_SQL + " WHERE taskId = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{taskId}, new TaskMapper());
    }


    @Override
    public void save(Task task) {
        String sql = "INSERT INTO TASK (homeworkId, taskDescription) VALUES (?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{task.getHomeworkId(), task.getTaskDescription()});
    }

    @Override
    public void update(Task task) {
        String sql = TaskMapper.EDIT_SQL + " WHERE getTaskId = ?";

        this.getJdbcTemplate().update(sql, task.getHomeworkId(), task.getTaskDescription(), task.getTaskId());
    }

    @Override
    public void deleteById(Long getTaskId) {
        String sql = "DELETE FROM TASK WHERE getTaskId = ?";

        this.getJdbcTemplate().update(sql, getTaskId);
    }
}


