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
import java.util.List;

@Repository
public class AttendanceDaoImpl extends GenericDaoImpl<Attendance> implements AttendanceDao {
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
        return new AttendanceMapper();
    }
}
