package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Schedule;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ScheduleMapper implements BaseObjectMapper<Schedule> {
    public static final String BASE_SQL =
            "SELECT * FROM Schedule ";

    @Override
    public Map<String, Object> mapObject(Schedule obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("id", obj.getScheduleId());
        res.put("group_id", obj.getGroupId());
        res.put("date_lesson", obj.getDate());
        res.put("lesson_id", obj.getLessonId());

        return res;
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

