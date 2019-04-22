package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    public static final String UPDATE_SQL
            = "UPDATE COURSE SET name = ?, course_level = ?, status = ?, date_start = ?,date_end = ?, group_number = ?, group_number = ?, min_number =?,description =?, trainer =?";
    public static String BASE_SQL
            = "Select Id, name, course_level, status, date_start, date_end, group_number, min_number, description, trainer From COURSE ";

    @Override
    public Course mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long id = rtS.getLong("Id");
        String name = rtS.getString("name");
        String course_level = rtS.getString("course_level");
        String status = rtS.getString("status");
        String date_start = rtS.getString("date_start");
        String date_end = rtS.getString("date_end");
        Long group_number = rtS.getLong("group_number");
        Long min_number = rtS.getLong("min_number");
        String description = rtS.getString("description");
        String trainer = rtS.getString("trainer");

        return new Course(id, name, course_level, status, date_start, date_end, group_number, min_number, description, trainer);
    }
}
