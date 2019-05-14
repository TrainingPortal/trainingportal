package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CourseMapper implements BaseObjectMapper<Course> {

    public static final String SELECT_SQL
            = "SELECT course_id, name, course_level, course_status_id, min_number, max_number, description, trainer_id FROM Course";

    public static final String EDIT_SQL
            = "UPDATE Course SET  name = ?, course_level = ?, course_status_id = ?, min_number = ?, max_number =?, description = ?, trainer_id = ?";

    public static final String INSERT_SQL
            = "INSERT INTO Course (name, course_level, course_status_id, min_number, max_number, description, trainer_id) VALUES (?,?,?,?,?,?,?)";

    public static final String DELETE
            ="DELETE FROM COURSE ";
    @Override
    public Course mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long courseId = resultSet.getLong("course_id");

        String courseName = resultSet.getString("name");

        String courseLevel = resultSet.getString("course_level");

        Long courseStatus = resultSet.getLong("course_status_id");

        int minNumber = resultSet.getInt("min_number");

        int maxNumber = resultSet.getInt("max_number");

        String description = resultSet.getString("description");

        Long trainerId = resultSet.getLong("trainer_id");

        return new Course(courseId, courseName, courseLevel, courseStatus, minNumber, maxNumber, description, trainerId);
    }

    @Override
    public Map<String, Object> mapObject(Course obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("course_id", obj.getCourseId());

        res.put("name",obj.getCourseName());

        res.put("course_level", obj.getCourseLevel());

        res.put("course_status_id", obj.getCourseStatus());

        res.put("min_number", obj.getMinNumber());

        res.put("max_number", obj.getMaxNumber());

        res.put("description", obj.getDescription());

        res.put("trainer_id", obj.getTrainerId());

        return res;
    }
}
