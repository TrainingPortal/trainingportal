package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.generic.GenericDaoImpl;
import trainingportal.mapper.MainCardMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Course;
import trainingportal.model.MainCardModel;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class MainCardDAOImpl extends GenericDaoImpl<MainCardModel> implements MainCardDao {

    private static final String TABLE_NAME = "main_card";
    private static final String ID_COLUMN = "main_card_id";

    private final BaseObjectMapper<MainCardModel> mainCardModelBaseObjectMapper;
    
    @Autowired
    public MainCardDAOImpl(DataSource dataSource, BaseObjectMapper<MainCardModel> mainCardModelBaseObjectMapper) {
        this.setDataSource(dataSource);
        setTable(TABLE_NAME);
        setPrimaryKey(ID_COLUMN);
        this.mainCardModelBaseObjectMapper = mainCardModelBaseObjectMapper;
    }

    @Override
    public List<MainCardModel> getAll() {
        String sql = MainCardMapper.SELECT_ALL_SQL + " ORDER BY main_card_id";
        if (this.getJdbcTemplate() != null) {
            return this.getJdbcTemplate().query(sql, new Object[]{}, mainCardModelBaseObjectMapper);
        } else
            return Collections.emptyList();
    }

    //insert into database new Card data
    @Override
    public void storeData(MainCardModel card) {
        String sql = MainCardMapper.INSERT_SQL;
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update(sql, card.getFilesName(), card.getFilesType(), card.getFilesData(),
                    card.getCardTitle(), card.getCardText(), card.getButtonName(), card.getCardUrl());
        }
    }

    @Override
    protected BaseObjectMapper<MainCardModel> getObjectMapper() {
        return mainCardModelBaseObjectMapper;
    }

}
