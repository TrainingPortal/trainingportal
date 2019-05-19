package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
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
    public Map<String, Object> mapObject(Schedule obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("id", obj.getScheduleId());
        res.put("group_id", obj.getScheduleGroupId());
        res.put("date_lesson", obj.getScheduleDate());
        res.put("lesson_id", obj.getScheduleLessonId());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }

    @Override
    public Schedule mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        Long groupId = resultSet.getLong("group_id");
        Date date = resultSet.getDate("date_lesson");
        Long lessonId = resultSet.getLong("lesson_id");

        return new Schedule(id, groupId,date,lessonId);
    }
}

