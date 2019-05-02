package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Homework;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeworkMapper implements RowMapper<Homework> {


    public static final String SELECT_SQL
            = "SELECT homeworkId, homeworkName, homeworkDeadlineDate FROM HOMEWORK";

    public static final String EDIT_SQL
            = "UPDATE HOMEWORK SET homeworkName = ?, homeworkDeadlineDate = ?";


    @Override
    public Homework mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long homeworkId = resultSet.getLong("homeworkId");

        String homeworkName = resultSet.getString("homeworkName");

        Date homeworkDeadlineDate = resultSet.getDate("homeworkDeadlineDate");


        return new Homework(homeworkId, homeworkName, homeworkDeadlineDate);
    }
}
