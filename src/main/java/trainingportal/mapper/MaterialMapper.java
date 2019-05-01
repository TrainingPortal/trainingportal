package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Material;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialMapper implements RowMapper<Material> {

    public static final String SELECT_SQL

            = "SELECT id, lesson_id, description FROM MATERIAL";

    public static final String EDIT_SQL
            = "UPDATE MATERIAL SET lesson_id = ?, description = ?";


    @Override
    public Material mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long materialId = resultSet.getLong("id");

        Long lessonId = resultSet.getLong("lesson_id");

        String materialDescription = resultSet.getString("description");

        return new Material(materialId, lessonId, materialDescription);
    }
}
