package trainingportal.dao;
import trainingportal.model.Weekday;
import java.util.List;

public interface WeekdayDao {

    List<Weekday> getWeekdayPeriodId(Long desiredPeriodId);

    List<Weekday> getAllAsPageByPeriodId(Long desiredPeriodId, int page, int total);

    int countAllByPeriodId(Long desiredPeriodId);
}

