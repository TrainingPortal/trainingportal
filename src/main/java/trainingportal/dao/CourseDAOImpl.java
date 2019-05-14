package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.CourseMapper;
import trainingportal.mapper.CourseStatusMapper;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class CourseDAOImpl extends JdbcDaoSupport implements CourseDao {

    @Autowired
    public CourseDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

// cant delete because of GenericDao<Course>
    @Override
    public List<Course> findAll() {
        String sql = CourseMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new CourseMapper());
    }

    // cant delete because of GenericDao<Course>
    @Override
    public Course findById(Long courseId) {
        String sql = CourseMapper.SELECT_SQL + " WHERE course_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{courseId}, new CourseMapper());
    }


    //insert into database new Course
    @Override
    public void save(Course course) {
        String sql = CourseMapper.INSERT_SQL;
        this.getJdbcTemplate().update(sql, new Object[]{course.getCourseName(), course.getCourseLevel(),
                course.getCourseStatus(), course.getMinNumber(), course.getMaxNumber(), course.getDescription(),
                course.getTrainerId(),});
    }

    @Override
    public void update(Course course) {
        String sql = CourseMapper.EDIT_SQL + " WHERE course_id = ?";

        this.getJdbcTemplate().update(sql, course.getCourseName(), course.getCourseLevel(),
                course.getCourseStatus(), course.getMinNumber(), course.getMaxNumber(), course.getDescription(),
                course.getTrainerId(), course.getCourseId());
    }

    @Override
    public void deleteById(Long courseId) {
        String sql = CourseMapper.DELETE + " WHERE course_id = ?";

        this.getJdbcTemplate().update(sql, courseId);
    }

    @Override
    public List<Course> getAllAsPage(int page, int total) {
        String sql = CourseMapper.SELECT_SQL + " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";
        return this.getJdbcTemplate().query(sql, new Object[]{}, new CourseMapper());
    }

    @Override
    public List<CourseStatus> getStatusList() {

        return this.getJdbcTemplate().query(CourseStatusMapper.SELECT_SQL, new CourseStatusMapper());
    }

    @Override
    public CourseStatus findStatusById(Long id) {

        String sql = CourseStatusMapper.SELECT_SQL + " WHERE id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new CourseStatusMapper());
    }

    @Override
    public int countAllByUserId(Long userId) {

        String sql = "SELECT COUNT(course_id) FROM Course WHERE trainer_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{userId}, Integer.class);
    }

    @Override
    public List<Course> getAllAsPageById(Long id, int page, int total) {

        String sql = CourseMapper.SELECT_SQL + " WHERE trainer_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{id}, new CourseMapper());
    }

    @Override
    public int countAll() {

        String sql = "SELECT COUNT(course_id) FROM Course";

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public List<Course> findCoursesByUserId(Long id) {

        /*String sql = "select a.course_id FROM groups a " +
                "INNER JOIN user_group b " +
                "ON a.id = b.group_id " +
                "WHERE b.user_id = ?";*/

        String sql = "SELECT a.course_id, a.name, a.course_level, a.course_status_id, a.min_number, " +
                "a.max_number, a.description, a.trainer_id FROM ((Course a " +
                "INNER JOIN groups b ON a.course_id = b.course_id) " +
                "INNER JOIN user_group c ON b.id = c.group_id) " +
                "WHERE c.user_id = ?";

        return this.getJdbcTemplate().query(sql, new Object[]{id}, new CourseMapper());
    }

    @Override
    public List<Course> getAllAsPageByEmployeeId(Long userId, int page, int total) {

        String sql = "SELECT a.course_id, a.name, a.course_level, a.course_status_id, a.min_number, " +
                "a.max_number, a.description, a.trainer_id FROM ((Course a " +
                "INNER JOIN groups b ON a.course_id = b.course_id) " +
                "INNER JOIN user_group c ON b.id = c.group_id) " +
                "WHERE c.user_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{userId}, new CourseMapper());
    }
}
