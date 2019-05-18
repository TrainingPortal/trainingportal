package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.ScheduleMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Schedule;
import trainingportal.model.Schedule;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ScheduleDaoImpl extends GenericDaoImpl<Schedule> implements ScheduleDao {
    //Define table and id column
    private static final String TABLE_NAME = "Schedule";
    private static final String ID_COLUMN = "id";

    @Autowired
    public ScheduleDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Schedule> getObjectMapper() {
        return new ScheduleMapper();
    }

    @Override
    public Schedule findByGroupId(Long id) {
        String sql = ScheduleMapper.BASE_SQL + " WHERE group_id = " + id;

        return this.getJdbcTemplate().queryForObject(sql,getObjectMapper());
    }

    @Override
    public List<Schedule> findAll() {
        String sql = ScheduleMapper.SELECT_SQL;

        return this.getJdbcTemplate().query(sql, new Object[]{}, new ScheduleMapper());
    }

    @Override
    public List<Schedule> getAllAsPageByGroupId(Long scheduleGroupId, int page, int total) {
//        String sql = ScheduleMapper.SELECT_SQL + " Where GROUP_ID = ? ";

        String sql = ScheduleMapper.SELECT_SQL + " WHERE GROUP_ID = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{scheduleGroupId}, new ScheduleMapper());
    }

    @Override
    public int countAllByGroupIdId(Long scheduleGroupId) {

        String sql = "SELECT COUNT(id) FROM SCHEDULE WHERE GROUP_ID = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{scheduleGroupId}, Integer.class);
    }

    @Override
    public Schedule findById(Long scheduleId) {
        String sql = ScheduleMapper.SELECT_SQL + " WHERE ID = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{scheduleId}, new ScheduleMapper());
    }

    @Override
    public void save(Schedule schedule) {
        String sql = ScheduleMapper.INSERT_SQL;
        this.getJdbcTemplate().update(sql, new Object[]{schedule.getScheduleGroupId(), schedule.getScheduleDate(),
        schedule.getScheduleLessonId()});
    }

    @Override
    public void update(Schedule schedule) {
        String sql = ScheduleMapper.EDIT_SQL + " WHERE id = ?";

        this.getJdbcTemplate().update(sql, schedule.getScheduleGroupId(), schedule.getScheduleDate(),
                schedule.getScheduleLessonId(), schedule.getScheduleId());
    }

    @Override
    public void deleteById(Long scheduleId) {
        String sql = ScheduleMapper.DELETE + " WHERE id = ?";

        this.getJdbcTemplate().update(sql, scheduleId);
    }
}
