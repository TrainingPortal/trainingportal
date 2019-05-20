package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.MainSliderMapper;
import trainingportal.model.MainSliderModel;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class MainSliderDAOImpl extends JdbcDaoSupport implements MainSliderDao {

    @Autowired
    public MainSliderDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<MainSliderModel> getAll() {
        String sql = MainSliderMapper.SELECT_ALL_SQL + " ORDER BY main_slider_id";
        return this.getJdbcTemplate().query(sql, new Object[]{}, new MainSliderMapper());
    }

    @Override
    public MainSliderModel findById(Long mainSliderId) {
        String sql = MainSliderMapper.SELECT_ALL_SQL + " WHERE mainSliderId = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{mainSliderId}, new MainSliderMapper());
    }


    //insert into database new Slide report
    @Override
    public void storeData(MainSliderModel slide) {
        String sql = MainSliderMapper.INSERT_SQL;
        this.getJdbcTemplate().update(sql, new Object[]{slide.getFilesName(), slide.getFilesType(), slide.getFilesData(),
                slide.getButtonName(), slide.getButtonUrl()});
    }

    @Override
    public void update(MainSliderModel slide) {
        String sql = MainSliderMapper.EDIT_SQL + " WHERE main_slider_id = ?";

        this.getJdbcTemplate().update(sql, slide.getFilesName(), slide.getFilesType(), slide.getFilesData(),
                slide.getFilesData(), slide.getButtonName(), slide.getButtonUrl());
    }

    @Override
    public void deleteById(Long mainSliderId) {
        String sql = MainSliderMapper.DELETE_SQL + " WHERE main_slider_id = ?";

        this.getJdbcTemplate().update(sql, mainSliderId);
    }

    @Override
    public int countAll() {

        String sql = MainSliderMapper.COUNT_ALL_SQL;

        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

}
