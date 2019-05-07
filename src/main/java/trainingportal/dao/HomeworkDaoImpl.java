package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.HomeworkMapper;
import trainingportal.model.Homework;

import javax.sql.DataSource;
import java.util.List;


@Repository
@Transactional
public class HomeworkDaoImpl extends JdbcDaoSupport implements HomeworkDao {


    @Autowired
    public HomeworkDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    //old realisation, work but need to rework(need to understand how to put in service)
    @Override
    public List<Homework> findAll() {
        String sql = HomeworkMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new HomeworkMapper());
    }

    @Override
    public List<Homework> getHomeworkLessonId(Long homeworkId) {
        String sql = HomeworkMapper.SELECT_SQL + " WHERE lesson_id = ?";

        List<Homework> homeworkList = this.getJdbcTemplate().query(sql, new Object[]{homeworkId}, new HomeworkMapper());
        return homeworkList;
    }

    @Override
    public Homework findById(Long homeworkId) {
        String sql = HomeworkMapper.SELECT_SQL + " WHERE homework_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{homeworkId}, new HomeworkMapper());
    }


    @Override
    public void save(Homework homework) {
        String sql = "INSERT INTO HOMEWORK ( lesson_id,homework_name, homework_deadline_date) VALUES (?,?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{homework.getLessonId(), homework.getHomeworkName(),
                homework.getHomeworkDeadlineDate()});
    }

    @Override
    public void update(Homework homework) {
        String sql = HomeworkMapper.EDIT_SQL + " WHERE homework_id = ?";

        this.getJdbcTemplate().update(sql, homework.getLessonId(), homework.getHomeworkName(), homework.getHomeworkDeadlineDate(), homework.getHomeworkId());
    }

    @Override
    public void deleteById(Long homeworkId) {
        String sql = "DELETE FROM HOMEWORK WHERE homework_id = ?";

        this.getJdbcTemplate().update(sql, homeworkId);
    }
}



