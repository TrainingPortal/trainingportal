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

        String lessonName = rtS.getString("lessonName");
        String lessonDescription = rtS.getString("lessonDescription");
        Double lessonDuration = rtS.getDouble("lessonDuration");
        String lessonDate = rtS.getString("lessonDate");
        Long homeworkId = rtS.getLong("homeworkId");
        Long groupId = rtS.getLong("groupId");


        return new Lesson(lessonId, lessonName, lessonDescription, lessonDuration, lessonDate, homeworkId, groupId);
    }
}