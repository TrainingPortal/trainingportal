package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.TaskMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Task;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {
    //Define table and id column
    private static final String TABLE_NAME = "task";
    private static final String ID_COLUMN = "id";

    @Autowired
    public TaskDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Task> getObjectMapper() {
        return null;
    }

    @Override
    public List<Task> getTaskLessonById(Long homeworkId) {
        String sql = TaskMapper.SELECT_SQL + " WHERE homeworkId = ?";
        List<Task> taskList = this.getJdbcTemplate().query(sql, new Object[]{homeworkId}, new TaskMapper());
        return taskList;
    }
}
