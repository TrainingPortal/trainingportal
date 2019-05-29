package trainingportal.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.InfoDeskMapper;
import trainingportal.mapper.QuestionStatusMapper;
import trainingportal.model.InfoDesk;
import trainingportal.model.QuestionStatus;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class InfoDeskDaoImpl extends JdbcDaoSupport implements InfoDeskDao {

    @Autowired
    public InfoDeskDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<InfoDesk> getListOfUsersThatNeedHelp(int page, int rowsPerPage) {
        String sql = InfoDeskMapper.SELECT_SQL + "  WHERE  STATUS_ID = 2 or STATUS_ID = 3 or STATUS_ID = 5  " +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{}, new InfoDeskMapper());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<InfoDesk> getListOfUsersThatHadAnswer( int page, int rowsPerPage) {
        String sql = InfoDeskMapper.SELECT_SQL + " WHERE  STATUS_ID = 4 "  +
                " OFFSET " + (page - 1) + " ROWS FETCH NEXT " + rowsPerPage + " ROWS ONLY";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{}, new InfoDeskMapper());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<InfoDesk> getQuestionsListOfUser( Long employeeId) {
        String sql = InfoDeskMapper.SELECT_SQL + " WHERE EMP_ID = ? ";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{employeeId}, new InfoDeskMapper());
        } else {
            return Collections.emptyList();
        }
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
            this.getJdbcTemplate().update(sql, infoDesk.getEmployeeId(), infoDesk.getInfoDeskQuestion(),
                    infoDesk.getInfoDeskStatusId());
        }
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(id) FROM Info_Desk";

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public int countAllByStatusId(Long infoDeskStatusId) {
        String sql = "SELECT COUNT(id) FROM Info_Desk WHERE  STATUS_ID = 2 or STATUS_ID = 5";

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    @Override
    public QuestionStatus findStatusById(Long id) {
        String sql = QuestionStatusMapper.SELECT_SQL + " WHERE id = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, new QuestionStatusMapper());
    }

    @Override
    public List<QuestionStatus> getStatusList() {

        return this.getJdbcTemplate().query(QuestionStatusMapper.SELECT_SQL, new QuestionStatusMapper());

    }

    @Override
    public InfoDesk findById(Long infoDeskId) {
        String sql = InfoDeskMapper.SELECT_SQL + " WHERE ID = ?";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().queryForObject(sql, new Object[]{infoDeskId}, new InfoDeskMapper());
        } else {
            return null;
        }
    }

    @Override
    public void update(InfoDesk infoDesk) {
        String sql = InfoDeskMapper.EDIT_SQL + " WHERE id = ?";

        this.getJdbcTemplate().update(sql, infoDesk.getEmployeeId(), infoDesk.getInfoDeskQuestion(),
                infoDesk.getInfoDeskAnswer(), infoDesk.getInfoDeskStatusId(), infoDesk.getInfoDeskId());

    }

    @Override
    public void deleteById(Long infoDeskId) {
        String sql = InfoDeskMapper.DELETE + " WHERE ID = ?";
        this.getJdbcTemplate().update(sql, new InfoDeskMapper());
    }

    @Override
    public List<InfoDesk> findAll() {
        String sql = InfoDeskMapper.SELECT_SQL;
        return this.getJdbcTemplate().query(sql, new Object[]{}, new InfoDeskMapper());
    }
}
