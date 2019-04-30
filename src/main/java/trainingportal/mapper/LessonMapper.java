package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonMapper implements RowMapper<Lesson> {


    public static final String EDIT_SQL
            = "UPDATE LESSON SET  lessonName = ?, lessonDescription = ?, lessonDuration = ?, lessonDate = ?, homeworkId=?,groupId=?";
    public static String SELECT_SQL
            = "SELECT lessonId, lessonName, lessonDescription, lessonDuration, lessonDate, homeworkId, groupId  From LESSON ";

    @Override
    public Lesson mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long lessonId = rtS.getLong("lessonId");
        String lessonName = rtS.getString("name");
        String lessonDescription = rtS.getString("course_level");
        Double lessonDuration = rtS.getDouble("lesson_description");
        String lessonDate = rtS.getString("lesson_date");
        Long homeworkId = rtS.getLong("homework_id");
        Long groupId = rtS.getLong("group_id");

        return new Lesson(lessonId, lessonName, lessonDescription, lessonDuration, lessonDate, homeworkId, groupId);
    }
}