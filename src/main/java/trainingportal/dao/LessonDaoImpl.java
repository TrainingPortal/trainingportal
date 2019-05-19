package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.LessonMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Lesson;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class LessonDaoImpl extends GenericDaoImpl<Lesson> implements LessonDao {

    //Define table and id column
    private static final String TABLE_NAME = "lesson";
    private static final String ID_COLUMN = "lesson_id";

    @Autowired
    private BaseObjectMapper<Lesson> lessonBaseObjectMapper;

    @Autowired
    public LessonDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Lesson> getObjectMapper() {
        return lessonBaseObjectMapper;
    }

    @Override
    public List<Lesson> getAllAsPageByCourseId(Long courseId, int page, int total) {
        String sql = LessonMapper.SELECT_SQL + " WHERE course_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return getLessons(courseId, sql);
    }

    @Override
    public List<Lesson> getLessonCourseId(Long courseId) {
        String sql = LessonMapper.SELECT_SQL + " WHERE course_id = ?";
        return getLessons(courseId, sql);
    }

    private List<Lesson> getLessons(Long courseId, String sql) {
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{courseId}, lessonBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public List<Lesson> getAllAsPageByTrainerId(Long userId, Long courseId, int page, int total) {

        String sql = LessonMapper.SELECT_SQL + " WHERE course_id = ?" +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return getLessons(courseId, sql);
    }

    @Override
    public int countAllByCourseId(Long courseId) {

        String sql = "SELECT COUNT(lesson_id) FROM Lesson WHERE course_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{courseId}, Integer.class);
        } else
            return 0;
    }

    @Override
    public Long getTrainerIdByCourseId(Long id) {

        String sql = "SELECT trainer_id from Course WHERE course_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, Long.class);
        } else
            return null;
    }

    @Override
    public Lesson getLessonByScheduleId(Long id) {
        String sql = "SELECT * FROM LESSON l INNER JOIN SCHEDULE s ON l.lesson_id = s.lesson_id  WHERE s.id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, getObjectMapper());
        } else
            return null;
    }

    @Override
    public Long getTrainerIdByLessonId(Long lessonId) {

        String sql = "SELECT a.trainer_id from Course a " +
                "INNER JOIN Lesson b ON a.course_id = b.course_id " +
                "WHERE b.lesson_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{lessonId}, Long.class);
    }
}

