package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.Weekday;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WeekdayMapper implements BaseObjectMapper<Weekday> {


    public static final String EDIT_SQL
            = "UPDATE WEEKDAY SET  day_name = ?, time_start = ?, time_end = ?, period_id = ?";
    public static String SELECT_SQL
            = "SELECT weekday_id, day_name , time_start , time_end, period_id From Weekday";

    @Override
    public Weekday mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long weekdayId = rtS.getLong("weekday_id");
        String dayName = rtS.getString("day_name");
        String timeStart = rtS.getString("time_start");
        String timeEnd = rtS.getString("time_end");
        Long desiredPeriodId = rtS.getLong("period_id");

        return new Weekday(weekdayId, dayName, timeStart, timeEnd, desiredPeriodId);
    }

    @Override
    public Map<String, Object> mapObject(Weekday obj) {
        Map<String, Object> res = new HashMap<>();

        res.put("weekday_id", obj.getWeekdayId());
        res.put("day_name", obj.getDayName());
        res.put("time_start", obj.getTimeStart());
        res.put("time_end", obj.getTimeEnd());
        res.put("period_id", obj.getPeriodId());

        return res;
    }

    @Override
    public String getSelectSql() {
        return SELECT_SQL;
    }

}
