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
    public List<UserGroup> getUserIdByLessonId(Long lessonId) {

        String sql = UserGroupMapper.SELECT_USERS_BY_LESSON_ID_SQL;

        return this.getJdbcTemplate().query(sql, new Object[]{lessonId}, new UserGroupMapper());
    }
}
