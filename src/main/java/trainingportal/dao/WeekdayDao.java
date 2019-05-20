package trainingportal.dao;
import trainingportal.model.Weekday;
import java.util.List;

public interface WeekdayDao {

    List<Weekday> findAll();

    List<Weekday> getWeekdayPeriodId(Long desiredPeriodId);

    Weekday findById(Long weekdayId);

    List<Weekday> getAllAsPageByPeriodId(Long desiredPeriodId, int page, int total);

    int  countAllByPeriodId(Long desiredPeriodId);

    void save(Weekday weekday);

    void update(Weekday weekday);

    void deleteById(Long weekdayId);
}

