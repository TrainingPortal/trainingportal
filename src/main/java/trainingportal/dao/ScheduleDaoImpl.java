package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.AttendanceMapper;
import trainingportal.mapper.ScheduleMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Role;
import trainingportal.model.Schedule;
import javax.sql.DataSource;
import java.text.CollationElementIterator;
import java.util.Collections;
import java.util.List;

@Repository
public class ScheduleDaoImpl extends GenericDaoImpl<Schedule> implements ScheduleDao {
    //Define table and id column
    private static final String TABLE_NAME = "Schedule";
    private static final String ID_COLUMN = "id";

    @Autowired
    private BaseObjectMapper<Schedule> scheduleBaseObjectMapper;

    @Autowired
    public ScheduleDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Schedule> getObjectMapper() {
        return scheduleBaseObjectMapper;
    }

    @Override
    public List<Schedule> findAllByGroupId(Long id) {
        String sql = ScheduleMapper.SELECT_SQL + " WHERE group_id = " + id;

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql,getObjectMapper());
        } else
            return Collections.emptyList();
    }

    @Override
    public List<Schedule> getAllAsPageByGroupId(Long scheduleGroupId, int page, int total) {
        String sql = ScheduleMapper.SELECT_SQL + " WHERE GROUP_ID = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{scheduleGroupId}, scheduleBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public int countAllByGroupId(Long scheduleGroupId) {
        String sql = "SELECT COUNT(id) FROM SCHEDULE WHERE GROUP_ID = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{scheduleGroupId}, Integer.class);
        } else
            return 0;
    }
}
