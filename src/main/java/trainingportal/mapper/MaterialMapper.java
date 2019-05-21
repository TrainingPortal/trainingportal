package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MaterialMapper implements BaseObjectMapper<Material> {

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

    @Override
    public Map<String, Object> mapObject(Material obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("lesson_id", obj.getLessonId());
        res.put("id", obj.getMaterialId());
        res.put("description",obj.getMaterialDescription());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }
}
