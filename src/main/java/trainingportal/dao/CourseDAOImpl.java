package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
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
    //don't forget if it needed, when DAOImpl extends JdbcDaoSupport
    @Autowired
    public CourseDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    //old realisation, work but need to rework(need to understand how to put in service)
    @Override
    public List<Course> findAll() {
        String sql = CourseMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new CourseMapper());
    }

    @Override
    public Course findById(Long courseId) {
        String sql = CourseMapper.SELECT_SQL + " WHERE courseId = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{courseId}, new CourseMapper());
    }


    //insert into database new Course
    @Override
    public void save(Course course) {
        String sql = "INSERT INTO COURSE (name, course_level, course_status_id, min_number, max_number, description, trainer_id, lessons_number) VALUES (?,?,?,?,?,?,?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{course.getCourseName(), course.getCourseLevel(),
                course.getCourseStatus(), course.getMinNumber(), course.getMaxNumber(), course.getDescription(),
                course.getTrainerId(), course.getLessonNumber()});
    }

    @Override
    public void update(Course course) {
        String sql = CourseMapper.EDIT_SQL + " WHERE courseId = ?";

        this.getJdbcTemplate().update(sql, course.getCourseName(), course.getCourseLevel(),
                course.getCourseStatus(), course.getMinNumber(), course.getMaxNumber(), course.getDescription(),
                course.getTrainerId(), course.getLessonNumber(), course.getCourseId());
    }

    @Override
    public void deleteById(Long courseId) {
        String sql = "DELETE FROM COURSE WHERE courseId = ?";

        this.getJdbcTemplate().update(sql, courseId);
    }
}
