package trainingportal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Attendance;
import trainingportal.model.AttendanceType;;

public class AttendanceTypeMapper implements BaseObjectMapper<AttendanceType> {

    public static final String BASE_SQL =
            "SELECT * FROM attendance_type";

    @Override
    public AttendanceType mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String type = resultSet.getString("type");

        return new AttendanceType(id,type);
    }

    @Override
    public Map<String, Object> mapObject(AttendanceType obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("id", obj.getAttendanceTypeId());
        res.put("type", obj.getType());

        return res;
    }
}
