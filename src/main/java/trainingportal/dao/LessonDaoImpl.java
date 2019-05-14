package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.LessonMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Lesson;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class LessonDaoImpl extends GenericDaoImpl<Lesson> implements LessonDao {

    //Define table and id column
    private static final String TABLE_NAME = "lesson";
    private static final String ID_COLUMN = "id";

    @Autowired
    public LessonDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Lesson> getObjectMapper() {
        return new LessonMapper();
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
    public List<Lesson> getAllAsPageByCourseId(Long courseId, int page, int total) {

        String sql = LessonMapper.SELECT_SQL + " WHERE course_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{courseId}, new LessonMapper());
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

    @Override
    public Long getTrainerIdByCourseId(Long id) {

        String sql = "SELECT trainer_id from Course WHERE course_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, Long.class);
    }

    @Override
    public Lesson getLessonByScheduleId(Long id) {
        String sql = "SELECT * FROM LESSON l INNER JOIN SCHEDULE s ON l.lesson_id = s.lesson_id  WHERE s.id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, getObjectMapper());
    }
}

