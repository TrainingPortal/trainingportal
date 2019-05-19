package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TaskMapper implements BaseObjectMapper<Task> {
    public static final String SELECT_SQL
            = "SELECT task_id, homework_id, task_description FROM TASK";

    public static final String EDIT_SQL
            = "UPDATE TASK SET  homework_id = ?, task_description = ? ";


    @Override
    public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long taskId = resultSet.getLong("task_id");
        String taskDescription = resultSet.getString("task_description");
        Long homeworkId = resultSet.getLong("homework_id");

        return new Task(taskId, taskDescription, homeworkId);
    }

    @Override
    public Map<String, Object> mapObject(Task obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("task_id", obj.getTaskId());
        res.put("task_description", obj.getTaskDescription());
        res.put("homework_id", obj.getHomeworkId());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}