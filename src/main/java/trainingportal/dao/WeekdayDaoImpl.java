
package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.MaterialMapper;
import trainingportal.mapper.WeekdayMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Material;
import trainingportal.model.Weekday;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class WeekdayDaoImpl extends GenericDaoImpl<Weekday> implements WeekdayDao {
    //Define table and id column
    private static final String TABLE_NAME = "weekday";
    private static final String ID_COLUMN = "weekday_id";

    @Autowired
    private BaseObjectMapper<Weekday> weekdayBaseObjectMapper;

    @Autowired
    public WeekdayDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Weekday> getObjectMapper() {
        return weekdayBaseObjectMapper;
    }

    @Override
    public List<Weekday> getWeekdayPeriodId(Long periodId) {
        String sql = WeekdayMapper.SELECT_SQL + " WHERE period_id = ?";
        return getWeekdays(periodId, sql);
    }

    @Override
    public List<Weekday> getAllAsPageByPeriodId(Long periodId, int page, int total) {
        String sql = WeekdayMapper.SELECT_SQL + " WHERE period_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return getWeekdays(periodId, sql);
    }

    private List<Weekday> getWeekdays(Long periodId, String sql) {
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{periodId}, weekdayBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public int countAllByPeriodId(Long periodId) {
        String sql = "SELECT COUNT(PERIOD_ID) FROM WEEKDAY WHERE PERIOD_ID = ?";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{periodId}, Integer.class);
        } else
            return 0;
    }
}


