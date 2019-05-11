package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import trainingportal.mapper.AttendanceTypeMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AttendanceTypeDaoImpl extends JdbcDaoSupport implements AttendanceTypeDao {

    @Autowired
    public AttendanceTypeDaoImpl(DataSource dataSource) { this.setDataSource(dataSource); }

    @Override
    public List<String> findAllReasonsList() {

        String sql = AttendanceTypeMapper.TYPE_SQL;
        List<String> allTypes = this.getJdbcTemplate().query(sql,new AttendanceTypeMapper());

        return allTypes;
    }
}
