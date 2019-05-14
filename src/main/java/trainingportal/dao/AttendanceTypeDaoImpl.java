package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.AttendanceTypeMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Attendance;
import trainingportal.model.AttendanceType;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AttendanceTypeDaoImpl extends GenericDaoImpl<AttendanceType> implements AttendanceTypeDao {
    //Define table and id column
    private static final String TABLE_NAME = "attendance_type";
    private static final String ID_COLUMN = "id";

    @Override
    protected BaseObjectMapper<AttendanceType> getObjectMapper() {
        return new AttendanceTypeMapper();
    }

    @Autowired
    public AttendanceTypeDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    public List<AttendanceType> findAllAttendanceList() {

        String sql = AttendanceTypeMapper.BASE_SQL;
        List<AttendanceType> allTypes = this.getJdbcTemplate().query(sql,new AttendanceTypeMapper());

        return allTypes;
    }
}
