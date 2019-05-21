package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.MainCardModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainCardMapper implements RowMapper<MainCardModel> {

    public static final String SELECT_ALL_SQL
            = "SELECT * FROM main_cards";

    public static final String INSERT_SQL
            = "INSERT INTO main_cards(files_name, files_type, files_data, card_title, card_text, button_name, card_url)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String EDIT_ALL_SQL
            = "UPDATE main_cards SET  files_name = ?, files_type = ?, files_data = ?, card_title = ?, card_text = ?," +
            " button_name = ?, card_url =?";

    public static final String EDIT_WITHOUT_FILE_SQL
            = "UPDATE main_cards SET card_title = ?, card_text = ?, button_name = ?, card_url = ?";


    @Override
    public MainCardModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long mainCardId = resultSet.getLong("main_card_id");

        String filesName = resultSet.getString("files_name");

        String filesType = resultSet.getString("files_type");

        byte[] filesData = resultSet.getBytes("files_data");

        String cardTitle = resultSet.getString("card_title");

        String cardText = resultSet.getString("card_text");

        String buttonName = resultSet.getString("button_name");

        String cardUrl = resultSet.getString("card_url");

        return new MainCardModel(mainCardId, filesName, filesType, filesData, cardTitle, cardText, buttonName, cardUrl);
    }
}
