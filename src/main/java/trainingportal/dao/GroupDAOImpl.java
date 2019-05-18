package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.GroupMapper;
import trainingportal.mapper.GroupStatusMapper;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class GroupDAOImpl extends JdbcDaoSupport implements GroupDao {

    @Autowired
    public GroupDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Group> GroupsList() {
        String sql = GroupMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new GroupMapper());
    }

    @Override
    public List<Group> getAllAsPageByCourseId(Long courseId, int page, int total) {
//            String sql = GroupMapper.SELECT_SQL + " WHERE  COURSE_ID = ? ";

        String sql = GroupMapper.SELECT_SQL + " WHERE course_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{courseId}, new GroupMapper());
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
        String sql = GroupMapper.EDIT_SQL + " WHERE id = ?";

        this.getJdbcTemplate().update(sql, group.getGroupName(), group.getGroupCapacity(),
                group.getCourseId(), group.getStatusId(), group.getGroupId());
    }

    @Override
    public void deleteGroupById(Long groupId) {
        String sql = "DELETE FROM groups WHERE id = ?";

        this.getJdbcTemplate().update(sql, groupId);
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
    public int countAll() {

        String sql = "SELECT COUNT(id) FROM Groups";

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public List<Group> getAllAsPage(int page, int total) {
        String sql = GroupMapper.SELECT_SQL + " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";
        return this.getJdbcTemplate().query(sql, new Object[]{}, new GroupMapper());
    }

    @Override
    public Long getTrainerIdByGroupId(Long groupId) {

        String sql = "SELECT MAX(a.trainer_id) FROM Course a " +
                "INNER JOIN Groups b " +
                "ON a.course_id = b.course_id " +
                "WHERE b.course_id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{groupId}, Long.class);
    }

    @Override
    public List<Group> getUserGroupsAsPageByCourseIdAndUserId(Long courseId, Long userId, int page, int total) {

        String sql = "SELECT a.id, a.name, a.capacity, a.course_id, a.status_id FROM Groups a " +
                "INNER JOIN Course b ON a.course_id = b.course_id " +
                "INNER JOIN User_Group c ON a.id = c.group_id " +
                "WHERE b.course_id = ? AND c.user_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{courseId, userId}, new GroupMapper());
    }
}
