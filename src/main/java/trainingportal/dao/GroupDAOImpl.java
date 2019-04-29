package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.GroupMapper;
import trainingportal.model.Group;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class GroupDAOImpl extends JdbcDaoSupport implements GroupDao {

    //don't forget if it needed, when DAOImpl extends JdbcDaoSupport
    @Autowired
    public GroupDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    //old realisation, work but need to rework(need to understand how to put in service)
    @Override
    public List<Group> GroupsList() {
        String sql = GroupMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new GroupMapper());
    }

    @Override
    public Group findGroupById(Long groupId) {
        String sql = GroupMapper.SELECT_SQL + " WHERE id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{groupId}, new GroupMapper());
    }


    //insert into database new Course
    @Override
    public void saveGroup(Group group) {
        String sql = "INSERT INTO groups (name, capacity, course_id, status_id) VALUES (?,?,?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{group.getGroupName(), group.getGroupCapacity(),
                group.getCourseId(), group.getStatusId()});
    }

    @Override
    public void editGroup(Group group) {
        String sql = GroupMapper.EDIT_SQL + " WHERE groupId = ?";

        this.getJdbcTemplate().update(sql, group.getGroupName(), group.getGroupCapacity(),
                group.getCourseId(), group.getStatusId());
    }

    @Override
    public void deleteGroupById(Long groupId) {
        String sql = "DELETE FROM groups WHERE groupId = ?";

        this.getJdbcTemplate().update(sql, groupId);
    }
}
