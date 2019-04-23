package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import trainingportal.mapper.GroupMapper;
import trainingportal.model.Group;

import javax.sql.DataSource;
import java.util.List;

/**
 *
 * @author Vitalii Chernetskyi
 */
@Repository
@Transactional
public class GroupDAO extends JdbcDaoSupport {
    
    @Autowired
    public GroupDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }
    
    public List<Group> getGroups(){
        // SELECT gr.id, gr.name, gr.trainer_id, gr.capacity FROM GROUPS gr
        String sql = GroupMapper.BASE_SQL;
        
        Object[] params = new Object[]{};
        GroupMapper mapper = new GroupMapper();
        List<Group> list = this.getJdbcTemplate().query(sql, params, mapper);
        
        return list;
    }
    
    public Group findGroup(String name){
        // SELECT gr.id, gr.name, gr.trainer_id, gr.capacity FROM GROUPS gr
        // Where gr.id = ?
        String sql = GroupMapper.BASE_SQL + "where gr.name = ?";
        
        Object[] params = new Object[]{ name };
        GroupMapper mapper = new GroupMapper();
        try{
            Group group = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return group;
        } catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    
    public void createGroup(String name, int trainer_id, int capacity){
        
        String sql = GroupMapper.INSERT_SQL;
        Object[] params = new Object[]{name, trainer_id, capacity};
        this.getJdbcTemplate().update(sql, params);
        
    }
    
    
    
    
}
