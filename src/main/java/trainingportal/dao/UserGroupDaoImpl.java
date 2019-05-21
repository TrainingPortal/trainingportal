package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.UserGroupMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.UserGroup;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class UserGroupDaoImpl extends GenericDaoImpl<UserGroup> implements UserGroupDao {
    @Autowired
    private BaseObjectMapper<UserGroup> userGroupBaseObjectMapper;

    @Autowired
    public UserGroupDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    protected BaseObjectMapper<UserGroup> getObjectMapper() {
        return userGroupBaseObjectMapper;
    }

    @Override
    public List<UserGroup> getUserIdByCourseId(Long courseId) {

        String sql = UserGroupMapper.SELECT_USERS_BY_COURSE_ID_SQL;

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{courseId}, userGroupBaseObjectMapper);
        } else
            return null;
    }

    @Override
    public List<UserGroup> getUserGroupListByGroupId(Long groupId) {
        String sql = UserGroupMapper.SELECT_USER_GROUP_LIST_BY_GROUP_ID;

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{groupId}, userGroupBaseObjectMapper);
        } else
            return null;
    }

    @Override
    public List<UserGroup> getUserIdByLessonId(Long lessonId) {

        String sql = UserGroupMapper.SELECT_USERS_BY_LESSON_ID_SQL;

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{lessonId}, new UserGroupMapper());
        } else
            return null;
    }

    @Override
    public void setUsersToGroup(Long groupId, Long userId) {

        String sql = "INSERT INTO User_Group (group_id, user_id) VALUES(?, ?)";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, groupId, userId);
        }

    }

    @Override
    public void deleteFromUserGroupByUserIdAndGroupId(Long userId, Long groupId) {

        String sql = "DELETE FROM User_Group WHERE user_id = ? AND group_id = ?";

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, userId, groupId);
        }
    }
}
