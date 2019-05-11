package trainingportal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Attendance;;

public class AttendanceTypeMapper implements RowMapper<Attendance> {

    public static final String BASE_SQL =
            "SELECT * FROM attendance_type";

    @Override
    public Attendance mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String type = resultSet.getString("type");

        return new Attendance(id,type);
    }
}
