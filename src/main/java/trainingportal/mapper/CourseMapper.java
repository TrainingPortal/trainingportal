package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {

    public static final String SELECT_SQL
            = "SELECT courseId, name, course_level, course_status, date_start, date_end, group_number, min_number, description, trainer FROM COURSE";

    public static final String EDIT_SQL
            = "UPDATE COURSE SET  name = ?, course_level = ?, course_status = ?, date_start = ?, date_end =?, group_number = ?, min_number = ?, description = ?, trainer = ?";

//    public static final String ADD_SQL
//            = "INSERT INTO COURSE (name, course_level, course_status, date_start, date_end, group_number, description, trainer) VALUES (?,?,?,?,?,?,?,?,?)";

    @Override
    public Course mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long courseId = resultSet.getLong("courseId");

        String courseName = resultSet.getString("name");

        String courseLevel = resultSet.getString("course_level");

        String courseStatus = resultSet.getString("course_status");

        String dateStart = resultSet.getString("date_start");

        String dateEnd = resultSet.getString("date_end");

        int groupNumber = resultSet.getInt("group_number");

        int minNumber = resultSet.getInt("min_number");

        String description = resultSet.getString("description");

        String courseTrainer = resultSet.getString("trainer");

        return new Course(courseId, courseName, courseLevel, courseStatus, dateStart, dateEnd, groupNumber, minNumber, description, courseTrainer);
    }
}
