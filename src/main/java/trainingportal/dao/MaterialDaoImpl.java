package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.MaterialMapper;
import trainingportal.model.Material;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class MaterialDaoImpl extends JdbcDaoSupport implements MaterialDao {


    @Autowired
    public MaterialDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Material> findAll() {
        String sql = MaterialMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new MaterialMapper());
    }

    @Override
    public List<Material> getMaterialLessonId(Long lessonId) {
        String sql = MaterialMapper.SELECT_SQL + " WHERE lesson_id = ?";
//        String sql = LessonMapper.SELECT_SQL + " INNER JOIN MATERIAL ON LESSON.lessonId = MATERIAL.lessonId and LESSON.lessonId=?";
        List<Material> materialList = this.getJdbcTemplate().query(sql, new Object[]{lessonId}, new MaterialMapper());
        return materialList;
    }

    @Override
    public Material findById(Long materialId) {
        String sql = MaterialMapper.SELECT_SQL + " WHERE id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{materialId}, new MaterialMapper());
    }


    @Override
    public void save(Material material) {
        String sql = "INSERT INTO MATERIAL (lesson_id, description) VALUES (?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{material.getLessonId(),
                material.getMaterialDescription(),});
    }

    @Override
    public void update(Material material) {
        String sql = MaterialMapper.EDIT_SQL + " WHERE id = ?";

        this.getJdbcTemplate().update(sql, material.getLessonId(),
                material.getMaterialDescription(), material.getMaterialId());
    }

    @Override
    public void deleteById(Long materialId) {
        String sql = "DELETE FROM MATERIAL WHERE id = ?";

        this.getJdbcTemplate().update(sql, materialId);
    }
}


