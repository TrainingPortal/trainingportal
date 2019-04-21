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
public class CourseDAOImpl extends JdbcDaoSupport {
    @Autowired
    public CourseDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Course> getCoursAll() {
        String sql = "SELECT * from COURSE";

        Object[] params = new Object[]{};
        CourseMapper courseMapper = new CourseMapper();
        List<Course> list = this.getJdbcTemplate().query(sql, params, courseMapper);

        return list;
    }

    public Course findCourseById(Long id) {
        String sql = "SELECT * FROM COURSE WHERE id = ?";
        Object[] params = new Object[]{id};
        CourseMapper courseMapper = new CourseMapper();
        try {
            Course course = this.getJdbcTemplate().queryForObject(sql, params, courseMapper);
            return course;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addCourse(Course course) {
        //Add Course
        String sql = "INSERT INTO COURSE (ID, NAME, COURSE_LEVEL,STATUS,DATE_START,DATE_END,GROUP_NUMBER,MINN_NUMBER,DESCRIPTION,TRAINER) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        this.getJdbcTemplate().update(sql, course.getId(), course.getCourseName(), course.getCourseLevel(),
                course.getDateStart(), course.getDateEnd(), course.getGroupNumber(),
                course.getMinNumber(), course.getDescription(), course.getTrainer());

    }

    public void updateCourse(Course course) {
        String sql = CourseMapper.BASE_SQL + "UPDATE WHERE Id = ?";

        this.getJdbcTemplate().update(sql,
                course.getId(), course.getCourseName(), course.getCourseLevel(),
                course.getDateStart(), course.getDateEnd(), course.getGroupNumber(),
                course.getMinNumber(), course.getDescription(), course.getTrainer());
    }


    public void deleteCourseById(Long Id) {
        String sql = CourseMapper.BASE_SQL + "DELETE  WHERE ID = ?";

        this.getJdbcTemplate().update(sql, Id);
    }

}
