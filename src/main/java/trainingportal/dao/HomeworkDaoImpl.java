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
    public Homework findById(Long homeworkId) {
        String sql = HomeworkMapper.SELECT_SQL + " WHERE homeworkId = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{homeworkId}, new HomeworkMapper());
    }


    @Override
    public void save(Homework homework) {
        String sql = "INSERT INTO HOMEWORK ( homeworkName, homeworkDeadlineDate) VALUES (?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{homework.getHomeworkName(),
                homework.getHomeworkDeadlineDate()});
    }

    @Override
    public void update(Homework homework) {
        String sql = HomeworkMapper.EDIT_SQL + " WHERE homeworkId = ?";

        this.getJdbcTemplate().update(sql, homework.getHomeworkName(), homework.getHomeworkDeadlineDate(), homework.getHomeworkId());
    }

    @Override
    public void deleteById(Long getHomeworkId) {
        String sql = "DELETE FROM HOMEWORK WHERE homeworkId = ?";

        this.getJdbcTemplate().update(sql, getHomeworkId);
    }
}



