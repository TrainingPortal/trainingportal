package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.MaterialMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Material;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class MaterialDaoImpl extends GenericDaoImpl<Material> implements MaterialDao {
    //Define table and id column
    private static final String TABLE_NAME = "material";
    private static final String ID_COLUMN = "id";

    @Autowired
    private BaseObjectMapper<Material> materialBaseObjectMapper;

    @Autowired
    public MaterialDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    protected BaseObjectMapper<Material> getObjectMapper() {
        return materialBaseObjectMapper;
    }

    @Override
    public List<Material> getMaterialLessonId(Long lessonId) {
        String sql = MaterialMapper.SELECT_SQL + " WHERE lesson_id = ?";
        return getMaterials(lessonId, sql);
    }

    @Override
    public List<Material> getAllAsPageByLessonId(Long lessonId, int page, int rowsPerPage) {
        String sql = MaterialMapper.SELECT_SQL + " WHERE lesson_id = ? " +
                "OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";

        return getMaterials(lessonId, sql);
    }

    private List<Material> getMaterials(Long lessonId, String sql) {
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{lessonId}, materialBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    public int countAllByLessonId(Long lessonId) {
        String sql = "SELECT COUNT(lesson_id) FROM Lesson WHERE LESSON_ID = ?";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{lessonId}, Integer.class);
        } else
            return 0;
    }
}


