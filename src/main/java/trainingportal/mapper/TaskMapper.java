package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    public static final String SELECT_SQL
            = "SELECT taskId, homeworkId, taskDescription FROM TASK";

    public static final String EDIT_SQL
            = "UPDATE TASK SET  homeworkId = ?, taskDescription = ? ";


    @Override
    public Task mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long taskId = resultSet.getLong("taskId");
        String taskDescription = resultSet.getString("taskDescription");
        Long homeworkId = resultSet.getLong("homeworkId");

        return new Task(taskId, taskDescription, homeworkId);
    }
}