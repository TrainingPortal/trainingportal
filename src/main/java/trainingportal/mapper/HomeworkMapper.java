package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Homework;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HomeworkMapper implements BaseObjectMapper<Homework> {


    public static final String SELECT_SQL
            = "SELECT homework_id, lesson_id, homework_name, homework_deadline_date FROM HOMEWORK";

    public static final String EDIT_SQL
            = "UPDATE HOMEWORK SET lesson_id = ?, homework_name = ?, homework_deadline_date = ?";


    @Override
    public Homework mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long homeworkId = resultSet.getLong("homework_id");

        Long lessonId = resultSet.getLong("lesson_id");

        String homeworkName = resultSet.getString("homework_name");

        Date homeworkDeadlineDate = resultSet.getDate("homework_deadline_date");


        return new Homework(homeworkId, lessonId, homeworkName, homeworkDeadlineDate);
    }

    @Override
    public Map<String, Object> mapObject(Homework obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("homework_id", obj.getHomeworkId());

        res.put("lesson_id",obj.getLessonId());

        res.put("homework_name", obj.getHomeworkName());

        res.put("homework_deadline_date", obj.getHomeworkDeadlineDate());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}
