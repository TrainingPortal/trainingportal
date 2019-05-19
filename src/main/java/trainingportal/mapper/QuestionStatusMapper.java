package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.QuestionStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionStatusMapper implements RowMapper<QuestionStatus> {

    public static final String SELECT_SQL
            = "SELECT id, name FROM Question_Status ";

    @Override
    public QuestionStatus mapRow(ResultSet rs, int rowNum) throws SQLException {

       Long questionId = rs.getLong("id");
       String questionStatusName = rs.getString("name");

        return new QuestionStatus(questionId,questionStatusName);
    }
}
