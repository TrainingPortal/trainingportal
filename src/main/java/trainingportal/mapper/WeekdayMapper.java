package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.Weekday;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class WeekdayMapper implements RowMapper<Weekday>{



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

        return new Weekday( weekdayId, dayName, timeStart,timeEnd, desiredPeriodId);
    }
}
