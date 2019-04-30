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

    //old realisation, work but need to rework(need to understand how to put in service)
    @Override
    public List<Material> findAll() {
        String sql = MaterialMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new MaterialMapper());
    }

    @Override
    public Material findById(Long materialId) {
        String sql = MaterialMapper.SELECT_SQL + " WHERE materialId = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{materialId}, new MaterialMapper());
    }


    @Override
    public void save(Material material) {
        String sql = "INSERT INTO MATERIAL (materialId, lessonId, materialDescription) VALUES (?,?,?)";
        this.getJdbcTemplate().update(sql, new Object[]{material.getMaterialId(), material.getMaterialDescription(),
                material.getLessonId()});
    }

    @Override
    public void update(Material material) {
        String sql = MaterialMapper.EDIT_SQL + " WHERE getMaterialId = ?";

        this.getJdbcTemplate().update(sql, material.getMaterialId(), material.getMaterialDescription(),
                material.getLessonId());
    }

    @Override
    public void deleteById(Long getMaterialId) {
        String sql = "DELETE FROM MATERIAL WHERE getMaterialId = ?";

        this.getJdbcTemplate().update(sql, getMaterialId);
    }
}


