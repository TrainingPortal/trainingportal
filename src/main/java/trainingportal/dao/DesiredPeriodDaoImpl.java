package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.DesiredPeriodMapper;
import trainingportal.model.DesiredPeriod;

import javax.sql.DataSource;

import java.util.List;
@Repository
@Transactional
public class DesiredPeriodDaoImpl extends JdbcDaoSupport implements DesiredPeriodDao {

    @Autowired
    public DesiredPeriodDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<DesiredPeriod> findAll() {
        String sql = DesiredPeriodMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new DesiredPeriodMapper());
    }

    @Override
    public List<DesiredPeriod> getAllAsPageByCourseId(Long courseId, int page, int total) {

        String sql = DesiredPeriodMapper.SELECT_SQL + " WHERE course_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{courseId}, new DesiredPeriodMapper());
    }

    @Override
    public List<DesiredPeriod> getPeriodCourseId(Long courseId) {

        String sql = DesiredPeriodMapper.SELECT_SQL + " WHERE course_id = ?";
        List<DesiredPeriod> periodList = this.getJdbcTemplate().query(sql, new Object[]{courseId}, new DesiredPeriodMapper());
        return periodList;
    }

    @Override
    public DesiredPeriod findById(Long desiredPeriodId) {
        String sql = DesiredPeriodMapper.SELECT_SQL + " WHERE DESIRED_PERIOD_ID = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{desiredPeriodId}, new DesiredPeriodMapper());
    }


    @Override
    public void save(DesiredPeriod desiredPeriod) {
        String sql = "INSERT INTO DESIRED_PERIOD ( course_id, USER_ID) VALUES (?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{desiredPeriod.getCourseId(), desiredPeriod.getUserId()});
    }

    @Override
    public void update(DesiredPeriod desiredPeriod) {
        String sql = DesiredPeriodMapper.EDIT_SQL + " WHERE DESIRED_PERIOD_ID = ?";

        this.getJdbcTemplate().update(sql, desiredPeriod.getCourseId(), desiredPeriod.getUserId());
    }

    @Override
    public void deleteById(Long desiredPeriodId) {
        String sql = "DELETE FROM DESIRED_PERIOD WHERE DESIRED_PERIOD_ID = ?";

        this.getJdbcTemplate().update(sql, desiredPeriodId);
    }

    @Override
    public List<DesiredPeriod> getAllAsPageByUserId(Long userId, Long courseId, int page, int total) {

        String sql = DesiredPeriodMapper.SELECT_SQL + " WHERE course_id = ?" +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{courseId}, new DesiredPeriodMapper());
    }

    @Override
    public int countAllByCourseId(Long courseId) {

        String sql = "SELECT COUNT(desired_period_id) FROM DESIRED_PERIOD WHERE course_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{courseId}, Integer.class);
    }


    @Override
    public Long getUserIdByCourseId(Long id) {

        String sql = "SELECT user_id from Course WHERE course_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, Long.class);
    }
}


