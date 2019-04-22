package trainingportal.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.CourseMapper;
import trainingportal.model.Course;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class CourseDAOImpl extends JdbcDaoSupport implements CourseDao {
    @Autowired
    public CourseDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Course> getCoursAll() {
        String sql = "SELECT * FROM COURSE";
//        String sql = CourseMapper.BASE_SQL;

        Object[] params = new Object[]{};
        CourseMapper courseMapper = new CourseMapper();
        List<Course> list = this.getJdbcTemplate().query(sql, params, courseMapper);

        return list;
    }

    @Override
    public Course findCourseById(Long id) {
        String sql = "SELECT * FROM COURSE where ID = ? ";
        Object[] params = new Object[]{id};
        CourseMapper courseMapper = new CourseMapper();
        try {
            Course course = this.getJdbcTemplate().queryForObject(sql, params, courseMapper);
            return course;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void addCourse(Course course) {
        //Add Course
        String sql = "INSERT INTO COURSE(name, course_level, status, date_start, date_end, group_number, min_number, description, trainer) VALUES (?,?,?,?,?,?,?,?,?)";
        this.getJdbcTemplate().update(sql, course.getId(), course.getCourse_level(), course.getStatus(),
                course.getDate_start(), course.getDate_end(), course.getGroup_number(),
                course.getMin_number(), course.getDescription(), course.getTrainer());

    }

    @Override
    public void updateCourseById(Long id) {
        String sql = "UPDATE COURSE SET name = ?, course_level = ?, status = ?, date_start = ?,date_end = ?, group_number = ?, group_number = ?, min_number =?,description =?, trainer =? WHERE Id = ? ";
        this.getJdbcTemplate().update(sql, id);
    }

    @Override
    public void deleteCourseById(Long Id) {
        String sql = "DELETE FROM COURSE WHERE Id = ?";

        this.getJdbcTemplate().update(sql, Id);
    }

    @Override
    public void saveCourse(Course course, Long Id) {

        String sql = "INSERT INTO COURSE(name, course_level, status, date_start, date_end, group_number, min_number, description, trainer) VALUES (?,?,?,?,?,?,?,?,?)";

        this.getJdbcTemplate().update(sql,
                course.getId(), course.getCourse_level(), course.getStatus(),
                course.getDate_start(), course.getDate_end(), course.getGroup_number(),
                course.getMin_number(), course.getDescription(), course.getTrainer());
    }

}
