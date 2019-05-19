package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.TaskMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Role;
import trainingportal.model.Task;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class TaskDaoImpl extends GenericDaoImpl<Task> implements TaskDao {
    //Define table and id column
    private static final String TABLE_NAME = "task";
    private static final String ID_COLUMN = "id";

    private final BaseObjectMapper<Task> taskBaseObjectMapper;

    @Autowired
    public TaskDaoImpl(DataSource dataSource, BaseObjectMapper<Task> taskBaseObjectMapper) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
        this.taskBaseObjectMapper = taskBaseObjectMapper;
    }

    @Override
    protected BaseObjectMapper<Task> getObjectMapper() {
        return taskBaseObjectMapper;
    }

    @Override
    public List<Task> getTaskLessonById(Long homeworkId) {
        String sql = TaskMapper.SELECT_SQL + " WHERE homeworkId = ?";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{homeworkId}, taskBaseObjectMapper);
        } else
            return Collections.emptyList();
    }
}
