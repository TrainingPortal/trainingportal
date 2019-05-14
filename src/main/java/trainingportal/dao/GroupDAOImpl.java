package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.CourseMapper;
import trainingportal.mapper.CourseStatusMapper;
import trainingportal.mapper.GroupMapper;
import trainingportal.mapper.GroupStatusMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Course;
import trainingportal.model.CourseStatus;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class GroupDAOImpl extends GenericDaoImpl<Group> implements GroupDao {


    //Define table and id column
    private static final String TABLE_NAME = "group";
    private static final String ID_COLUMN = "id";

    @Autowired
    public GroupDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    public List<GroupStatus> getStatusList() {

        return this.getJdbcTemplate().query(GroupStatusMapper.SELECT_SQL, new GroupStatusMapper());
    }

    @Override
    public GroupStatus findStatusById(Long id) {

        String sql = GroupStatusMapper.SELECT_SQL + " WHERE id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new GroupStatusMapper());
    }

    @Override
    public List<Group> findAllByCourseId(Long id) {
        String sql = GroupMapper.SELECT_SQL + " WHERE course_id = ?";

        return this.getJdbcTemplate().query(sql, new Object[]{id}, new GroupMapper());
    }

    @Override
    protected BaseObjectMapper<Group> getObjectMapper() {
        return new GroupMapper();
    }
}
