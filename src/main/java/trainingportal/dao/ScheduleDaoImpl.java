package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.ScheduleMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Schedule;
import trainingportal.model.Schedule;

import javax.sql.DataSource;

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
}
