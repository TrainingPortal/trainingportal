package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.QuestionStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionStatusMapper implements RowMapper<QuestionStatus> {


    @Override
    public QuestionStatus mapRow(ResultSet rs, int rowNum) throws SQLException {


        return null;
    }
}
