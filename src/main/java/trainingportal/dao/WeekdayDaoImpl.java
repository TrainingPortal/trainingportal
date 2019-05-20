package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import trainingportal.mapper.WeekdayMapper;

import trainingportal.model.Weekday;

import javax.sql.DataSource;
import java.util.List;
@Repository
@Transactional

public class WeekdayDaoImpl extends JdbcDaoSupport implements WeekdayDao {


        @Autowired
        public WeekdayDaoImpl(DataSource dataSource) {
            this.setDataSource(dataSource);
        }

    @Override
    public List<Weekday> findAll() {
        String sql = WeekdayMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new WeekdayMapper());
    }

    @Override
    public List<Weekday> getWeekdayPeriodId(Long desiredPeriodId) {
        String sql = WeekdayMapper.SELECT_SQL + " WHERE period_id = ?";
        List<Weekday> weekdayList = this.getJdbcTemplate().query(sql, new Object[]{desiredPeriodId}, new WeekdayMapper());
        return weekdayList;
    }
    @Override
    public Weekday findById(Long weekdayId) {
        String sql = WeekdayMapper.SELECT_SQL + " WHERE id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{weekdayId}, new WeekdayMapper());
    }

    @Override
    public List<Weekday> getAllAsPageByPeriodId(Long desiredPeriodId, int page, int total) {
        String sql = WeekdayMapper.SELECT_SQL + " WHERE period_id = ? " +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{desiredPeriodId}, new WeekdayMapper());
    }
    @Override
    public int countAllByPeriodId(Long desiredPeriodId) {
        String sql = "SELECT COUNT(PERIOD_ID) FROM WEEKDAY WHERE PERIOD_ID = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{desiredPeriodId}, Integer.class);
    }


        @Override
        public void save(Weekday weekday) {
            String sql = "INSERT INTO Weekday (day_name, time_start, time_end) VALUES (?,?,?)";
            this.getJdbcTemplate().update(sql, weekday.getWeekdayId(), weekday.getDayName(),weekday.getTimeStart(), weekday.getTimeEnd(), weekday.getPeriodId());
            }

        @Override
        public void update(Weekday weekday) {
            String sql = WeekdayMapper.EDIT_SQL + " WHERE WEEKDAY_ID = ?";

            this.getJdbcTemplate().update(sql, weekday.getWeekdayId(), weekday.getDayName(),weekday.getTimeStart(), weekday.getTimeEnd(), weekday.getPeriodId());
        }

        @Override
        public void deleteById(Long weekdayId) {
            String sql = "DELETE FROM Weekday WHERE weekday_id = ?";

            this.getJdbcTemplate().update(sql, weekdayId);
        }
    }



