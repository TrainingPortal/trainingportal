package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonMapperNew implements RowMapper<Lesson> {


    public static final String EDIT_SQL
            = "UPDATE LESSON SET  lessonName = ?, lessonDescription = ?, lessonDuration = ?, homeworkId=?, courseId=?, lesson_number=?";
    public static String SELECT_SQL
            = "SELECT lessonId, lessonName, lessonDescription, lessonDuration,  homeworkId, courseId, lesson_number  From LESSON ";

    @Override
    public Lesson mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long lessonId = rtS.getLong("lessonId");
        String lessonName = rtS.getString("lessonName");
        String lessonDescription = rtS.getString("lessonDescription");
        Double lessonDuration = rtS.getDouble("lessonDuration");
        Long homeworkId = rtS.getLong("homeworkId");
        Long courseId = rtS.getLong("courseId");
        int lessonNumber = rtS.getInt("lesson_number");

        return new Lesson(lessonId, lessonName, lessonDescription, lessonDuration, homeworkId, courseId, lessonNumber);
    }
}