package trainingportal.mapper;

import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Attendance;
import trainingportal.model.AttendanceType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AttendanceMapper implements BaseObjectMapper<Attendance> {

    public static final String BASE_SQL =
            "SELECT * FROM attendance";


    @Override
    public Map<String, Object> mapObject(Attendance obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("id", obj.getAttendanceTypeId());
        res.put("user_id", obj.getUserId());
        res.put("type_id", obj.getAttendanceTypeId());
        res.put("schedule_id", obj.getScheduleId());

        return res;
    }

    @Override
    public Attendance mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        Long userId = resultSet.getLong("user_id");
        Long presenceId = resultSet.getLong("type_id");
        Long scheduleId = resultSet.getLong("schedule_id");

        return new Attendance(id, userId,presenceId,scheduleId);
    }
}
