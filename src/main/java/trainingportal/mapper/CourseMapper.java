package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    public static final String BASE_SQL
            = "Select cu.Id, cu.name, cu.course_level, cu.status, cu.date_start, cu.date_end, cu.group_number, cu.minn_number, cu.description, cu.trainer From COURSE cu";

    @Override
    public Course mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long id = rtS.getLong("Id");
        String courseName = rtS.getString("name");
        String courseLevel = rtS.getString("course_level");
        String courseStatus = rtS.getString("status");
        String dateStart = rtS.getString("date_start");
        String dateEnd = rtS.getString("date_end");
        int groupNumber = rtS.getInt("group_number");
        int minNumber = rtS.getInt("minn_number");
        String description = rtS.getString("description");
        String trainer = rtS.getString("trainer");

        return new Course(id, courseName, courseLevel, courseStatus, dateStart, dateEnd, groupNumber, minNumber, description, trainer);
    }
}
