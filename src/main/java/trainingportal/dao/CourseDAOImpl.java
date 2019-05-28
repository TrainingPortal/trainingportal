package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.CourseMapper;
import trainingportal.mapper.CourseStatusMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class CourseDAOImpl extends GenericDaoImpl<Course> implements CourseDao {

    //Define table and id column
    private static final String TABLE_NAME = "course";
    private static final String ID_COLUMN = "course_id";

    @Autowired
    private BaseObjectMapper<Course> courseBaseObjectMapper;
    @Autowired
    private BaseObjectMapper<CourseStatus> courseStatusBaseObjectMapper;

    @Autowired
    public CourseDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    public List<CourseStatus> getStatusList() {

        List<CourseStatus> courses;
        if (this.getJdbcTemplate() != null) {
            courses = this.getJdbcTemplate().query(CourseStatusMapper.SELECT_SQL, courseStatusBaseObjectMapper);
            return courses;
        } else
            return Collections.emptyList();
    }

    @Override
    public CourseStatus findStatusById(Long id) {

        String sql = CourseStatusMapper.SELECT_SQL + " WHERE id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, courseStatusBaseObjectMapper);
    }

    @Override
    public List<Course> findByTrainerId(Long id) {
        String sql = CourseMapper.SELECT_SQL + " WHERE trainer_id = ?";
        return getCourses(id, sql);
    }

    protected List<Course> getCourses(Long id, String sql) {
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{id}, courseBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    protected Course getCourse(Long id, String sql) {
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, courseBaseObjectMapper);
        } else
            return null;
    }

    public int countAllByTrainerId(Long trainerId) {

        String sql = "SELECT COUNT(course_id) FROM Course WHERE trainer_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{trainerId}, Integer.class);
        } else
            return 0;
    }

    public int countAllByUserId(Long userId) {

        String sql = "SELECT COUNT(a.course_id) FROM ((Course a " +
                "INNER JOIN groups b ON a.course_id = b.course_id) " +
                "INNER JOIN user_group c ON b.id = c.group_id) " +
                "WHERE c.user_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{userId}, Integer.class);
        } else
            return 0;
    }

    @Override
    public List<Course> getAllAsPageById(Long id, int page, int rowsPerPage) {

        String sql = CourseMapper.SELECT_SQL + " WHERE trainer_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";

        return getCourses(id, sql);
    }

    @Override
    protected BaseObjectMapper<Course> getObjectMapper() {
        return courseBaseObjectMapper;
    }

    @Override
    public List<Course> findCoursesByUserId(Long id) {
        String sql = "SELECT a.course_id, a.name, a.course_level, a.course_status_id, a.min_number, " +
                "a.max_number, a.description, a.trainer_id FROM ((Course a " +
                "INNER JOIN groups b ON a.course_id = b.course_id) " +
                "INNER JOIN user_group c ON b.id = c.group_id) " +
                "WHERE c.user_id = ?";

        return getCourses(id, sql);
    }

    @Override
    public List<Course> getAllAsPageByEmployeeId(Long userId, int page, int rowsPerPage) {

        String sql = "SELECT a.course_id, a.name, a.course_level, a.course_status_id, a.min_number, " +
                "a.max_number, a.description, a.trainer_id FROM ((Course a " +
                "INNER JOIN groups b ON a.course_id = b.course_id) " +
                "INNER JOIN user_group c ON b.id = c.group_id) " +
                "WHERE c.user_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";

        return getCourses(userId, sql);
    }

    @Override
    public Course findCourseIdByGroupId(Long groupId) {

        String sql = "SELECT a.course_id, a.name, a.course_level, a.course_status_id, a.min_number, a.max_number, " +
                "a.description, a.trainer_id FROM Course a " +
                "INNER JOIN Groups b " +
                "ON a.course_id = b.course_id " +
                "WHERE b.id = ?";

        return getCourse(groupId, sql);
    }
}
