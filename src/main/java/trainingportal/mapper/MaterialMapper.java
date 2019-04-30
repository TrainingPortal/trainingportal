package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Material;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialMapper implements RowMapper<Material> {

    public static final String SELECT_SQL
            = "SELECT materialId, lessonId, materialDescription FROM MATERIAL";

    public static final String EDIT_SQL
            = "UPDATE MATERIAL SET  materialId = ?, lessonId = ?, materialDescription = ?";


    @Override
    public Material mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long materialId = resultSet.getLong("materialId");

        Long lessonId = resultSet.getLong("lessonId");

        String materialDescription = resultSet.getString("materialDescription");


        return new Material(materialId, lessonId, materialDescription);
    }
}
