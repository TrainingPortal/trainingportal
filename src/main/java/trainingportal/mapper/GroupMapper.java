package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Vitalii Chernetskyi
 */
public class GroupMapper implements RowMapper<Group> {
    
    public static final String BASE_SQL
            = "SELECT gr.id, gr.name, gr.trainer_id, gr.capacity FROM groups gr";
    
    public static final String INSERT_SQL
            = "INSERT INTO groups (name, trainer_id, capacity) VALUES (?, ?, ?)";
    
    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int trainer_id = rs.getInt("trainer_id");
        int capacity = rs.getInt("capacity");
        
        return new Group(id, name, trainer_id, capacity);
        
    }
    
}
