package trainingportal.mapper;

import org.springframework.jdbc.core.RowMapper;
import trainingportal.model.DesiredPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DesiredPeriodMapper implements RowMapper<DesiredPeriod> {
//    public static final String BASE_SQL =
//            "SELECT * FROM desired_period";


    public static final String EDIT_SQL
            = "UPDATE LESSON SET  user_id = ?, course_id = ?";
    public static String SELECT_SQL
            = "SELECT desired_period_id, user_id, course_id From Desired_Period ";

    @Override
    public DesiredPeriod mapRow(ResultSet rtS, int rowNum) throws SQLException {
        Long desiredPeriodId = rtS.getLong("desired_period_id");
        Long userId = rtS.getLong("user_id");
        Long courseId = rtS.getLong("course_id");

        return new DesiredPeriod(desiredPeriodId, userId, courseId);
    }
}

