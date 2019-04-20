package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rSet, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rSet.getInt("Id"));
        course.setCourseName(rSet.getString("courseName"));
        course.setCourseLevel(rSet.getString("courseLevel"));
        course.setCourseStatus(rSet.getString("courseStatus"));
        course.setDateStart(rSet.getDate("date_start"));
        course.setDateEnd(rSet.getDate("date_end"));
        course.setGroupNumber(rSet.getInt("groupNumber"));
        course.setMinNumber(rSet.getInt("minNumber"));
        course.setDescription(rSet.getString("description"));
        course.setTrainer(rSet.getString("trainer"));

        return null;
    }

}
