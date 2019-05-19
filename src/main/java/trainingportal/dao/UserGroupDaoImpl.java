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

    final BaseObjectMapper<UserGroup> userGroupBaseObjectMapper;

    @Autowired
    public UserGroupDaoImpl(DataSource dataSource, BaseObjectMapper<UserGroup> userGroupBaseObjectMapper) {
        this.setDataSource(dataSource);
        this.userGroupBaseObjectMapper = userGroupBaseObjectMapper;
    }

    @Override
    protected BaseObjectMapper<UserGroup> getObjectMapper() {
        return userGroupBaseObjectMapper;
    }

    @Override
    public List<UserGroup> getUserIdByCourseId(Long courseId) {

        String sql = UserGroupMapper.SELECT_USERS_SQL;

        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{courseId}, userGroupBaseObjectMapper);
        } else
            return null;
    }
}
