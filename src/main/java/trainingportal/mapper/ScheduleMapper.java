package trainingportal.mapper;

<<<<<<< HEAD
import trainingportal.mapper.generic.BaseObjectMapper;
=======
import org.springframework.jdbc.core.RowMapper;
>>>>>>> 3cccdae2520769102e55cfee0b93a3f1ace01dd3
import trainingportal.model.Schedule;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ScheduleMapper implements BaseObjectMapper<Schedule> {
    public static final String SELECT_SQL
            = "SELECT id, group_id, date_lesson, lesson_id  FROM Schedule";

    public static final String EDIT_SQL
            = "UPDATE Schedule SET  group_id = ?, date_lesson = ?, lesson_id =?  ";

    public static final String INSERT_SQL
            = "INSERT INTO Schedule ( group_id, date_lesson, lesson_id) VALUES (?,?,?)";

    public static final String DELETE
            ="DELETE FROM Schedule";

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long scheduleId = rs.getLong("id");
        Long scheduleGroupId = rs.getLong("group_id");
        Date scheduleDate = rs.getDate("date_lesson");
        Long scheduleLessonId = rs.getLong("lesson_id");

        return new Schedule(scheduleId,scheduleGroupId,scheduleDate,scheduleLessonId);
    }
}

