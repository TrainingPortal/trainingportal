package trainingportal.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.InfoDeskMapper;
import trainingportal.model.InfoDesk;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class InfoDeskDaoImpl extends JdbcDaoSupport implements InfoDeskDao {

    @Autowired
    public InfoDeskDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<InfoDesk> getAllAsPage(int page, int rowsPerPage) {
        String sql = InfoDeskMapper.SELECT_SQL + " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";
        return this.getJdbcTemplate().query(sql, new Object[]{}, new InfoDeskMapper());
    }

    @Override
    public void save(InfoDesk infoDesk) {
        String sql = InfoDeskMapper.INSERT_SQL;
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, infoDesk.getEmployeeId(), infoDesk.getInfoDeskDescription(),
                    infoDesk.getInfoDeskStatusId());
        }
    }

    @Override
    public List<InfoDesk> getRequestForHelpByEmpId(Long employeeId) {
        String sql = InfoDeskMapper.SELECT_SQL + " WHERE EMP_ID = ?";
        List<InfoDesk> infoDeskList = this.getJdbcTemplate().query(sql, new Object[]{employeeId}, new InfoDeskMapper());
        return infoDeskList;
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(id) FROM Info_Desk";

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public InfoDesk findById(Long id) {
        return null;
    }

    @Override
    public void update(InfoDesk infoDesk) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<InfoDesk> findAll() {
        return null;
    }
}
