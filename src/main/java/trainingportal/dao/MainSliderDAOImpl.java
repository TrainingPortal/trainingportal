package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.MainSliderMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.MainSliderModel;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class MainSliderDAOImpl extends GenericDaoImpl<MainSliderModel> implements MainSliderDao {

    private static final String TABLE_NAME = "main_slider";
    private static final String ID_COLUMN = "main_slider_id";

    @Autowired
    private BaseObjectMapper<MainSliderModel> mainSliderModelBaseObjectMapper ;

    @Autowired
    public MainSliderDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
    }

    @Override
    public List<MainSliderModel> getAll() {
        String sql = MainSliderMapper.SELECT_ALL_SQL + MainSliderMapper.ORDER_ID;
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{}, mainSliderModelBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    @Override
    protected BaseObjectMapper<MainSliderModel> getObjectMapper() {
        return mainSliderModelBaseObjectMapper;
    }

    @Override
    public void updateAll(MainSliderModel slide) {
        String sql = MainSliderMapper.EDIT_ALL_SQL + MainSliderMapper.WHERE_ID;

        this.getJdbcTemplate().update(sql, slide.getFilesName(), slide.getFilesType(), slide.getFilesData(),
                slide.getButtonName(), slide.getButtonUrl(), slide.getCaptionHeader(), slide.getCaptionText(),
                slide.getSlideInterval(), slide.getMainSliderId());
    }

    @Override
    public void updateWithoutFile(MainSliderModel slide) {
        String sql = MainSliderMapper.EDIT_WITHOUT_FILE_SQL + MainSliderMapper.WHERE_ID;

        this.getJdbcTemplate().update(sql, slide.getButtonName(), slide.getButtonUrl(), slide.getCaptionHeader(),
                slide.getCaptionText(), slide.getSlideInterval(), slide.getMainSliderId());
    }

    @Override
    public void deleteById(Long mainSliderId) {
        String sql = MainSliderMapper.DELETE_SQL + MainSliderMapper.WHERE_ID;

        this.getJdbcTemplate().update(sql, mainSliderId);
    }
  
    //insert into database new Slide data
    @Override
    public void storeData(MainSliderModel slide) {
        String sql = MainSliderMapper.INSERT_SQL;

        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, slide.getFilesName(), slide.getFilesType(), slide.getFilesData(),
                    slide.getButtonName(), slide.getButtonUrl(), slide.getCaptionHeader(), slide.getCaptionText(),
                    slide.getSlideInterval());
        }

    }

}
