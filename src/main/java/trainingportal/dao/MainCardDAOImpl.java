package trainingportal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.mapper.MainCardMapper;
import trainingportal.model.MainCardModel;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class MainCardDAOImpl extends JdbcDaoSupport implements MainCardDao {

    @Autowired
    public MainCardDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<MainCardModel> getAll() {
        String sql = MainCardMapper.SELECT_ALL_SQL + " ORDER BY main_card_id";
        return this.getJdbcTemplate().query(sql, new Object[]{}, new MainCardMapper());
    }

    @Override
    public MainCardModel findById(Long mainCardId) {
        String sql = MainCardMapper.SELECT_ALL_SQL + " WHERE mainCardId = ?";

        return this.getJdbcTemplate().queryForObject(sql, new Object[]{mainCardId}, new MainCardMapper());
    }


    //insert into database new Card data
    @Override
    public void storeData(MainCardModel card) {
        String sql = MainCardMapper.INSERT_SQL;
        this.getJdbcTemplate().update(sql, new Object[]{card.getFilesName(), card.getFilesType(), card.getFilesData(),
                card.getCardTitle(), card.getCardText(), card.getButtonName(), card.getCardUrl()});
    }

    @Override
    public void updateAll(MainCardModel card) {
        String sql = MainCardMapper.EDIT_ALL_SQL + " WHERE main_card_id = ?";

        this.getJdbcTemplate().update(sql, card.getFilesName(), card.getFilesType(), card.getFilesData(),
                card.getCardTitle(), card.getCardText(), card.getButtonName(), card.getCardUrl(), card.getMainCardId());
    }

    @Override
    public void updateWithoutFile(MainCardModel card) {
        String sql = MainCardMapper.EDIT_WITHOUT_FILE_SQL + " WHERE main_card_id = ?";

        this.getJdbcTemplate().update(sql, card.getCardTitle(), card.getCardText(), card.getButtonName(),
                card.getCardUrl(), card.getMainCardId());
    }

    @Override
    public void deleteById(Long mainCardId) {
        String sql = "DELETE FROM main_cards WHERE main_card_id = ?";

        this.getJdbcTemplate().update(sql, mainCardId);
    }

}
