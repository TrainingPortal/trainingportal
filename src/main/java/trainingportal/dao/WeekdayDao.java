package trainingportal.dao;
import trainingportal.model.Weekday;
import java.util.List;

public interface WeekdayDao {

    List<Weekday> getWeekdayPeriodId(Long periodId);

    List<Weekday> getAllAsPageByPeriodId(Long periodId, int page, int total);

    int countAllByPeriodId(Long periodId);
}

