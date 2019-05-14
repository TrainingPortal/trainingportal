package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.UserGroupMapper;
import trainingportal.mapper.UserMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.User;
import trainingportal.model.UserGroup;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class UserGroupDAOImpl extends GenericDaoImpl<UserGroup> implements UserGroupDao{
    @Autowired
    PasswordEncoder passwordEncoder;
    //Define table and id column
    private static final String TABLE_NAME = "user_group";
    private static final String ID_COLUMN = "id";

    public UserGroupDAOImpl(DataSource dataSource) {
        super(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<UserGroup> getObjectMapper() {
        return new UserGroupMapper();
    }
}