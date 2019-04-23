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

    //work
    @Override
    public List<Course> getCoursAll() {
//        String sql = "SELECT * FROM COURSE";
        String sql = CourseMapper.BASE_SQL;

        Object[] params = new Object[]{};
        CourseMapper courseMapper = new CourseMapper();
        try {
            List<Course> list = this.getJdbcTemplate().query(sql, params, courseMapper);
            return list;
        } catch (NullPointerException e) {
            return null;
        }
    }

    //dont find where to use
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
        String sql = "INSERT INTO COURSE(ID,NAME, COURSE_LEVEL, STATUS, DATE_START, DATE_END, GROUP_NUMBER, MIN_NUMBER, DESCRIPTION, TRAINER) VALUES (?,?,?,?,?,?,?,?,?)";
        this.getJdbcTemplate().update(sql, course.getId(), course.getName(), course.getCourse_level(), course.getStatus(),
                course.getDate_start(), course.getDate_end(), course.getGroup_number(),
                course.getMin_number(), course.getDescription(), course.getTrainer());

    }

    @Override
    public void editCourseById(Course course) {
//        String sql = "UPDATE COURSE SET name = ?, course_level = ?, status = ?, date_start = ?,date_end = ?, group_number = ?,min_number =?,description =?, trainer =? WHERE Id = ? ";
        String sql = "UPDATE COURSE set NAME = ?, COURSE_LEVEL = ?, STATUS = ?, DATE_START = ?,DATE_END = ?, GROUP_NUMBER = ?, MIN_NUMBER = ?, DESCRIPTION =?, TRAINER =? WHERE ID=?";

        this.getJdbcTemplate().update(sql, course.getName(), course.getCourse_level(), course.getStatus(),
                course.getDate_start(), course.getDate_end(), course.getGroup_number(),
                course.getMin_number(), course.getDescription(), course.getTrainer(), course.getId());
    }


    @Override
    public void saveCourse(Course course) {

        String sql = "INSERT INTO COURSE(NAME, COURSE_LEVEL, STATUS, DATE_START, DATE_END, GROUP_NUMBER, MIN_NUMBER, DESCRIPTION, TRAINER) VALUES (?,?,?,?,?,?,?,?,?)";

        this.getJdbcTemplate().update(sql,
                course.getId(), course.getName(), course.getCourse_level(), course.getStatus(),
                course.getDate_start(), course.getDate_end(), course.getGroup_number(),
                course.getMin_number(), course.getDescription(), course.getTrainer());
    }

    @Override
    public void save(Course course, Long Id) {

        String sql = "INSERT INTO COURSE(ID,NAME, COURSE_LEVEL, STATUS, DATE_START, DATE_END, GROUP_NUMBER, MIN_NUMBER, DESCRIPTION, TRAINER) VALUES (?,?,?,?,?,?,?,?,?)";

        this.getJdbcTemplate().update(sql, new Object[]{course.getId(), course.getName(), course.getCourse_level(), course.getStatus(),
                course.getDate_start(), course.getDate_end(), course.getGroup_number(),
                course.getMin_number(), course.getDescription(), course.getTrainer()});
    }

    @Override
    public void deleteCourseById(Long Id) {
        String sql = "DELETE FROM COURSE WHERE Id = ?";

        this.getJdbcTemplate().update(sql, Id);
    }
}
