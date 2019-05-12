package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.schedule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<schedule> {


    @Override
    public schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

        return null;
    }
}
