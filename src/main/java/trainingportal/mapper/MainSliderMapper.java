package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.MainSliderModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainSliderMapper implements BaseObjectMapper<MainSliderModel> {

    public static final String SELECT_ALL_SQL
            = "SELECT * FROM main_slider";

    public static final String INSERT_SQL
            = "INSERT INTO main_slider(files_name, files_type, files_data, button_name, button_url) VALUES (?, ?, ?, ?, ?)";

    public static final String EDIT_ALL_SQL
            = "UPDATE main_slider SET  files_name = ?, files_type = ?, files_data = ?, button_name = ?, button_url = ?";

    public static final String EDIT_WITHOUT_FILE_SQL
            = "UPDATE main_slider SET button_name = ?, button_url = ?";

    public static final String COUNT_ALL_SQL
            = "SELECT COUNT (*) FROM main_slider";

    public static final String DELETE_SQL
            = "DELETE FROM main_slider";



    @Override
    public MainSliderModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long mainSliderId = resultSet.getLong("main_slider_id");
        String filesName = resultSet.getString("files_name");
        String filesType = resultSet.getString("files_type");
        byte[] filesData = resultSet.getBytes("files_data");
        String buttonName = resultSet.getString("button_name");
        String buttonUrl = resultSet.getString("button_url");

        return new MainSliderModel(mainSliderId, filesName, filesType, filesData, buttonName, buttonUrl);
    }

    @Override
    public Map<String, Object> mapObject(MainSliderModel obj) {

        Map<String, Object> res = new HashMap<>();

        res.put("main_slider_id", obj.getMainSliderId());
        res.put("files_name", obj.getFilesName());
        res.put("files_type", obj.getFilesType());
        res.put("files_data", obj.getFilesData());
        res.put("button_name", obj.getButtonName());
        res.put("button_url", obj.getButtonUrl());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_ALL_SQL;
    }
}
