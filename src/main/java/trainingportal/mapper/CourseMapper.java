package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {

    public static final String SELECT_SQL
            = "SELECT courseId, name, course_level, course_status_id, min_number, max_number, description, trainer_id, lessons_number FROM Course";

    public static final String EDIT_SQL
            = "UPDATE Course SET  name = ?, course_level = ?, course_status_id = ?, min_number = ?, max_number =?, description = ?, trainer_id = ?, lessons_number = ?";


    @Override
    public Course mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long courseId = resultSet.getLong("courseId");

        String courseName = resultSet.getString("name");

        String courseLevel = resultSet.getString("course_level");

        Long courseStatus = resultSet.getLong("course_status_id");

        int minNumber = resultSet.getInt("min_number");

        int maxNumber = resultSet.getInt("max_number");

        String description = resultSet.getString("description");

        int trainerId = resultSet.getInt("trainer_id");

        int lessonNumber = resultSet.getInt("lessons_number");

        return new Course(courseId, courseName, courseLevel, courseStatus, minNumber, maxNumber, description, trainerId, lessonNumber);
    }
}
