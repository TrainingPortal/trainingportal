package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.CourseRowMapper;
import trainingportal.model.Course;

import java.util.List;

@Transactional
@Repository
public class CourseDAOImpl implements CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Course> getAllCourse() {
        String query = "SELECT * FROM COURSES";
        RowMapper<Course> rowMapper = new CourseRowMapper();
        List<Course> list = jdbcTemplate.query(query, rowMapper);
        return list;
    }

    @Override
    public Course findCourseById(Integer Id) {
        String query = "SELECT * FROM COURSES WHERE courseId = ?";
        RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        Course course = jdbcTemplate.queryForObject(query, rowMapper, Id);
        return course;
    }

    @Override
    public void addCourse(Course course) {
        String query = "INSERT INTO COURSES(Id, courseName, courseLevel, courseStatus, dateStart, dateEnd, groupNumber, minNumber, description, trainer ) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query, course.getId(), course.getCourseName(), course.getCourseLevel(),
                course.getCourseStatus(), course.getDateStart(), course.getDateEnd(), course.getGroupNumber(),
                course.getMinNumber(), course.getDescription(), course.getTrainer());
    }

    @Override
    public void updateCourse(Course course) {
        String query = "UPDATE COURSES SET courseName=?, courseLevel=?, courseStatus=?, dateStart=?, dateEnd=?, groupNumber=?, minNumber=?, description=?, trainer=? WHERE ID=?";
        jdbcTemplate.update(query, course.getCourseName(), course.getCourseLevel(),
                course.getCourseStatus(), course.getDateStart(), course.getDateEnd(), course.getGroupNumber(),
                course.getMinNumber(), course.getDescription(), course.getTrainer(), course.getId());

    }

    @Override
    public void deleteCourse(Integer Id) {
        String query = "DELETE FROM  COURSES WHERE Id=?";
        jdbcTemplate.update(query, Id);
    }

}
