package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.InfoDesk;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoDeskMapper implements RowMapper<InfoDesk> {

    public static final String SELECT_SQL
            = "SELECT id, emp_id, question, answer, status_id  FROM Info_Desk";

    public static final String EDIT_SQL
            = "UPDATE Info_Desk SET  emp_id = ?, question = ?, answer = ?, status_id = ? ";

    public static final String INSERT_SQL
            = "INSERT INTO Info_Desk (emp_id, question, status_id) VALUES (?,?,?)";

    public static final String DELETE
            ="DELETE FROM Info_Desk ";

    @Override
    public InfoDesk mapRow(ResultSet rs, int rowNum) throws SQLException {
       Long infoDeskId = rs.getLong("id");
       Long employeeId = rs.getLong("emp_id");
       String infoDeskQuestion = rs.getString("question");
       String infoDeskAnswer = rs.getString("answer");
       Long infoDeskStatusId = rs.getLong("status_id");

        return new InfoDesk(infoDeskId,employeeId,infoDeskQuestion, infoDeskAnswer,infoDeskStatusId);
    }
}
