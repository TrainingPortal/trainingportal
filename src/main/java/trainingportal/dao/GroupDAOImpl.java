package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.GroupMapper;
import trainingportal.mapper.GroupStatusMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class GroupDAOImpl extends GenericDaoImpl<Group> implements GroupDao {
    @Autowired
    private BaseObjectMapper<Group> groupBaseObjectMapper;

    //Define table and id column
    private static final String TABLE_NAME = "groups";
    private static final String ID_COLUMN = "id";

    @Autowired
    public GroupDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    public List<Group> GroupsList() {
        String sql = GroupMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, groupBaseObjectMapper);
    }

    @Override
    public List<Group> getAllAsPageByCourseId(Long courseId, int page, int rowsPerPage) {
//            String sql = GroupMapper.SELECT_SQL + " WHERE  COURSE_ID = ? ";

        String sql = GroupMapper.SELECT_SQL + " WHERE course_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{courseId}, groupBaseObjectMapper);
    }

    @Override
    public Group findGroupById(Long groupId) {
        String sql = GroupMapper.SELECT_SQL + " WHERE id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{groupId}, groupBaseObjectMapper);
        }
        else return null;
    }

    @Override
    public void saveGroup(Group group) {
        String sql = "INSERT INTO GROUPS (name, capacity, course_id, status_id) VALUES (?,?,?,?)";
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, new Object[]{group.getGroupName(), group.getGroupCapacity(),
                    group.getCourseId(), group.getStatusId()});
        }
    }

    @Override
    public void editGroup(Group group) {
        String sql = GroupMapper.EDIT_SQL + " WHERE id = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, group.getGroupName(), group.getGroupCapacity(),
                    group.getCourseId(), group.getStatusId(), group.getGroupId());
        }
    }

    @Override
    public void deleteGroupById(Long groupId) {
        String sql = "DELETE FROM groups WHERE id = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, groupId);
        }
    }

    @Override
    public List<GroupStatus> getStatusList() {

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(GroupStatusMapper.SELECT_SQL, new GroupStatusMapper());
        }
        else return null;
    }

    @Override
    public GroupStatus findStatusById(Long id) {

        String sql = GroupStatusMapper.SELECT_SQL + " WHERE id = ?";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new GroupStatusMapper());
        }
        else return null;
    }

    @Override
    public List<Group> findAllByCourseId(Long id) {
        String sql = GroupMapper.SELECT_SQL + " WHERE course_id = ?";

        return this.getJdbcTemplate().query(sql, new Object[]{id}, groupBaseObjectMapper);
    }

    @Override
    protected BaseObjectMapper<Group> getObjectMapper() {
        return groupBaseObjectMapper;
    }

    @Override
    public Long getTrainerIdByGroupId(Long groupId) {

        String sql = "SELECT a.trainer_id FROM Course a " +
                "INNER JOIN Groups b " +
                "ON a.course_id = b.course_id " +
                "WHERE b.id = ?";


            return this.getJdbcTemplate().queryForObject(sql, new Object[]{groupId}, Long.class);

    }

    @Override
    public List<Group> getUserGroupsAsPageByCourseIdAndUserId(Long courseId, Long userId, int page, int rowsPerPage) {

        String sql = "SELECT a.id, a.name, a.capacity, a.course_id, a.status_id FROM Groups a " +
                "INNER JOIN Course b ON a.course_id = b.course_id " +
                "INNER JOIN User_Group c ON a.id = c.group_id " +
                "WHERE b.course_id = ? AND c.user_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{courseId, userId}, groupBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public int countAllGroupWithoutPage(Long courseId) {
        String sql = " SELECT COUNT(id) FROM groups " +
                "WHERE id not in (SELECT GROUPS.id from Groups INNER JOIN CHAT ON " +
                "GROUPS.id = CHAT.group_id) AND course_id = ?";

        return this.getJdbcTemplate().queryForObject(sql,new Object[]{courseId}, Integer.class);
    }


    @Override
    public List<Group> getGroupsPageWithoutChat(Long courseId, int page, int total) {
        String sql = "SELECT id, name, capacity, course_id, status_id FROM groups WHERE id not in" +
                " (SELECT GROUPS.id from Groups INNER JOIN CHAT ON GROUPS.id = CHAT.group_id) AND course_id = ? ";

            return this.getJdbcTemplate().query(sql,new Object[]{courseId}, groupBaseObjectMapper);


    }
    @Override
    public List<Group> findAllGroupsWithoutChatByCourseId(Long id) {
        String sql = "SELECT id, name, capacity, course_id, status_id FROM groups WHERE id not in (SELECT GROUPS.id from Groups INNER JOIN CHAT ON GROUPS.id = CHAT.group_id) AND course_id = ? ";


            return this.getJdbcTemplate().query(sql, new Object[]{id}, groupBaseObjectMapper);

    }

}
