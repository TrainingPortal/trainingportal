package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.InfoDesk;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoDeskMapper implements RowMapper<InfoDesk> {


    @Override
    public InfoDesk mapRow(ResultSet rs, int rowNum) throws SQLException {
//       Long infoDeskId = rs.getLong("id");
//       Long employeeId = rs.getLong();
//       String infoDeskDescription;
//       Long infoDeskStatusId;

        return null;
    }
}
