package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.HomeworkMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Homework;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;


@Repository
@Transactional
public class HomeworkDaoImpl extends GenericDaoImpl<Homework> implements HomeworkDao {
    @Autowired
    private BaseObjectMapper<Homework> homeworkBaseObjectMapper;

    //Define table and id column
    private static final String TABLE_NAME = "homework";
    private static final String ID_COLUMN = "id";

    @Autowired
    public HomeworkDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }
    @Override
    protected BaseObjectMapper<Homework> getObjectMapper() {
        return homeworkBaseObjectMapper;
    }

    @Override
    public List<Homework> getHomeworkLessonId(Long homeworkId) {
        String sql = HomeworkMapper.SELECT_SQL + " WHERE lesson_id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{homeworkId}, homeworkBaseObjectMapper);
        } else
            return Collections.emptyList();
    }
}



