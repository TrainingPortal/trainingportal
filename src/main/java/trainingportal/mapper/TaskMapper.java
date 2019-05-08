package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
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
}