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

    @Override
    public List<Lesson> getAllAsPageByCourseId(Long courseId, int page, int total) {

        String sql = LessonMapper.SELECT_SQL + " WHERE course_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{courseId}, new LessonMapper());
    }

    @Override
    public List<Lesson> getLessonCourseId(Long courseId) {

        String sql = LessonMapper.SELECT_SQL + " WHERE course_id = ?";
        List<Lesson> lessonList = this.getJdbcTemplate().query(sql, new Object[]{courseId}, new LessonMapper());
        return lessonList;
    }

    @Override
    public Lesson findById(Long lessonId) {
        String sql = LessonMapper.SELECT_SQL + " WHERE lesson_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{lessonId}, new LessonMapper());
    }


    @Override
    public void save(Lesson lesson) {
        String sql = "INSERT INTO Lesson (lesson_name, lesson_description, lesson_duration, course_id, lesson_number) VALUES (?,?,?,?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{lesson.getLessonName(), lesson.getLessonDescription(),
                lesson.getLessonDuration(), lesson.getCourseId(), lesson.getLessonNumber()});
    }

    @Override
    public void update(Lesson lesson) {
        String sql = LessonMapper.EDIT_SQL + " WHERE lesson_id = ?";

        this.getJdbcTemplate().update(sql, lesson.getLessonName(), lesson.getLessonDescription(),
                lesson.getLessonDuration(), lesson.getCourseId(), lesson.getLessonNumber(), lesson.getLessonId());
    }

    @Override
    public void deleteById(Long lessonId) {
        String sql = "DELETE FROM LESSON WHERE lesson_id = ?";

        this.getJdbcTemplate().update(sql, lessonId);
    }

    @Override
    public List<Lesson> getAllAsPageByTrainerId(Long userId, Long courseId, int page, int total) {

        String sql = LessonMapper.SELECT_SQL + " WHERE course_id = ?" +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{courseId}, new LessonMapper());
    }

    @Override
    public int countAllByCourseId(Long courseId) {

        String sql = "SELECT COUNT(lesson_id) FROM Lesson WHERE course_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{courseId}, Integer.class);
    }

    /*@Override
    public boolean isConnectedWithTrainer(Long id) {
        String sql = "SELECT COUNT(lesson_id) FROM Lesson a " +
                "INNER JOIN Course b " +
                "ON b.trainer_id = ? AND a.course_id = b.course_id";
        Long lessons = this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, Long.class);

        if(lessons>0)
            return true;
        else
            return false;
    }*/

    @Override
    public Long getTrainerIdByCourseId(Long id) {

        String sql = "SELECT trainer_id from Course WHERE course_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, Long.class);
    }
}

