package trainingportal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AttendanceTypeMapper implements RowMapper<String> {

    public static final String TYPE_SQL=
            "SELECT type FROM attendance_type";

    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        String result = resultSet.getString("type");
        return result;
    }
}
