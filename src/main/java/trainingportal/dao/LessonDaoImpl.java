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
    public Lesson findById(Long lessonId) {
        String sql = LessonMapper.SELECT_SQL + " WHERE lessonId = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{lessonId}, new LessonMapper());
    }


    @Override
    public void save(Lesson lesson) {
        String sql = "INSERT INTO Lesson (lessonName, lessonDescription, lessonDuration, lessonDate, homeworkId, groupId) VALUES (?,?,?,?,?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{lesson.getLessonName(), lesson.getLessonDuration(),
                lesson.getLessonDuration(), lesson.getLessonDate(), lesson.getGroupId()});
    }

    @Override
    public void update(Lesson lesson) {
        String sql = LessonMapper.EDIT_SQL + " WHERE lessonId = ?";

        this.getJdbcTemplate().update(sql, lesson.getLessonName(), lesson.getLessonDuration(),
                lesson.getLessonDate(), lesson.getHomeworkId(), lesson.getGroupId());
    }

    @Override
    public void deleteById(Long lessonId) {
        String sql = "DELETE FROM LESSON WHERE lessonId = ?";

        this.getJdbcTemplate().update(sql, lessonId);
    }
}

