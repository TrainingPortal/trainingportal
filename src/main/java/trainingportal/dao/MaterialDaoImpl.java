package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.MaterialMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Material;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class MaterialDaoImpl extends GenericDaoImpl<Material> implements MaterialDao {
    //Define table and id column
    private static final String TABLE_NAME = "material";
    private static final String ID_COLUMN = "id";

    @Autowired
    public MaterialDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Material> getObjectMapper() {
        return new MaterialMapper();
    }

    @Override
    public List<Material> getMaterialLessonId(Long lessonId) {
        String sql = MaterialMapper.SELECT_SQL + " WHERE lesson_id = ?";
        List<Material> materialList = this.getJdbcTemplate().query(sql, new Object[]{lessonId}, new MaterialMapper());
        return materialList;
    }

    @Override
    public List<Material> getAllAsPageByLessonId(Long lessonId, int page, int total) {
        String sql = MaterialMapper.SELECT_SQL + " WHERE lesson_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + total + " ROWS ONLY";

        return this.getJdbcTemplate().query(sql, new Object[]{lessonId}, new MaterialMapper());
    }

    @Override
    public int countAllByLessonId(Long lessonId) {
        String sql = "SELECT COUNT(lesson_id) FROM Lesson WHERE LESSON_ID = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{lessonId}, Integer.class);
    }
}


