package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.AttendanceMapper;
import trainingportal.mapper.AttendanceTypeMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Attendance;
import trainingportal.model.AttendanceType;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
public class AttendanceDaoImpl extends GenericDaoImpl<Attendance> implements AttendanceDao {

    @Autowired
    private BaseObjectMapper<Attendance> attendanceBaseObjectMapper;

    //Define table and id column
    private static final String TABLE_NAME = "attendance";
    private static final String ID_COLUMN = "id";

    @Autowired
    public AttendanceDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Attendance> getObjectMapper() {
        return attendanceBaseObjectMapper;
    }

    @Override
    public List<Attendance> findAttendancesByScheduleId(Long scheduleId) {
        String sql = AttendanceMapper.SELECT_SQL + " at INNER JOIN SCHEDULE sc ON at.SCHEDULE_ID = sc.ID " +
                "WHERE sc.ID = ?";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql,new Object[]{scheduleId},attendanceBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public List<Attendance> getSubordinatesAttendancesByManager(Long managerId) {
        String sql = AttendanceMapper.SELECT_SQL + " at INNER JOIN USERS us ON us.USER_ID = at.USER_ID " +
                "WHERE us.MANAGER_ID = ?";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql,new Object[]{managerId},attendanceBaseObjectMapper);
        } else
            return Collections.emptyList();
    }
}
