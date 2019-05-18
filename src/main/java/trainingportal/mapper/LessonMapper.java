package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LessonMapper implements BaseObjectMapper<Lesson> {


    public static final String EDIT_SQL
            = "UPDATE LESSON SET  lesson_name = ?, lesson_description = ?, lesson_duration = ?, course_id=?, lesson_number=?";
    public static String SELECT_SQL
            = "SELECT lesson_id, lesson_name, lesson_description, lesson_duration, course_id, lesson_number  From LESSON ";

    @Override
    public Lesson mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long lessonId = rtS.getLong("lesson_id");
        String lessonName = rtS.getString("lesson_name");
        String lessonDescription = rtS.getString("lesson_description");
        Double lessonDuration = rtS.getDouble("lesson_duration");
        Long courseId = rtS.getLong("course_id");
        int lessonNumber = rtS.getInt("lesson_number");

        return new Lesson(lessonId, lessonName, lessonDescription, lessonDuration, courseId, lessonNumber);
    }

    @Override
    public Map<String, Object> mapObject(Lesson obj) {

        Map<String, Object> res = new HashMap<>();

        res.put("lesson_id", obj.getLessonId());
        res.put("lesson_name", obj.getLessonName());
        res.put("lesson_description",obj.getLessonDescription());
        res.put("lesson_duration", obj.getLessonDuration());
        res.put("course_id", obj.getCourseId());
        res.put("lesson_number",obj.getLessonNumber());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}