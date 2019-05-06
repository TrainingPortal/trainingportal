package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.LessonMapper;
import trainingportal.model.Lesson;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class LessonDaoImpl extends JdbcDaoSupport implements LessonDao {

    @Autowired
    public LessonDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Lesson> findAll() {
        String sql = LessonMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new LessonMapper());
    }

    //        here first courseId is from Lesson and it = courseId from COURSE. This whole method needed
//        to show current list of Lessons which belong to Course with particular courseId
//        String sql = LessonMapper.SELECT_SQL +
//                "WHERE courseId = (SELECT courseId From Course WHERE COURSE.courseId = ?)";
    @Override
    public List<Lesson> getLessonCourseId(Long courseId) {
        String sql = LessonMapper.SELECT_SQL + " WHERE courseId = ?";
//        String sql = LessonMapper.SELECT_SQL + " INNER JOIN COURSE ON LESSON.courseId = COURSE.COURSEID and LESSON.COURSEID=?";
        List<Lesson> lessonList = this.getJdbcTemplate().query(sql, new Object[]{courseId}, new LessonMapper());
        return lessonList;
    }

    @Override
    public Lesson findById(Long lessonId) {
        String sql = LessonMapper.SELECT_SQL + " WHERE lessonId = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{lessonId}, new LessonMapper());
    }


    @Override
    public void save(Lesson lesson) {
        String sql = "INSERT INTO Lesson (lessonName, lessonDescription, lessonDuration,  homeworkId, courseId, lesson_number) VALUES (?,?,?,?,?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{lesson.getLessonName(), lesson.getLessonDescription(),
                lesson.getLessonDuration(), lesson.getHomeworkId(), lesson.getCourseId(), lesson.getLessonNumber()});
    }

    @Override
    public void update(Lesson lesson) {
        String sql = LessonMapper.EDIT_SQL + " WHERE lessonId = ?";

        this.getJdbcTemplate().update(sql, lesson.getLessonName(), lesson.getLessonDescription(),
                lesson.getLessonDuration(), lesson.getHomeworkId(), lesson.getCourseId(),
                lesson.getLessonNumber(), lesson.getLessonId());
    }

    @Override
    public void deleteById(Long lessonId) {
        String sql = "DELETE FROM LESSON WHERE lessonId = ?";

        this.getJdbcTemplate().update(sql, lessonId);
    }

}

