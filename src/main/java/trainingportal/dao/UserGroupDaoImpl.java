package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.UserGroupMapper;
import trainingportal.model.UserGroup;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class UserGroupDaoImpl extends JdbcDaoSupport implements UserGroupDao {


    @Autowired
    public UserGroupDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<UserGroup> getUserIdByCourseId(Long courseId) {

        String sql = UserGroupMapper.SELECT_USERS_SQL;

        return this.getJdbcTemplate().query(sql, new Object[]{courseId}, new UserGroupMapper());
    }
}
