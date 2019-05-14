package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
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
        return null;
    }

    @Override
    public List<Lesson> getLessonCourseId(Long courseId) {

        String sql = LessonMapper.SELECT_SQL + " WHERE course_id = ?";
        List<Lesson> lessonList = this.getJdbcTemplate().query(sql, new Object[]{courseId}, new LessonMapper());
        return lessonList;
    }
}

