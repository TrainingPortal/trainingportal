package trainingportal.service;

import trainingportal.model.Weekday;
import trainingportal.service.generic.GenericService;

import java.util.List;


public interface WeekdayService extends GenericService<Weekday> {

    int getNumberOfPages(List<Weekday> users, double total);

    List<Weekday> getWeekdayPeriodId(Long periodId);

    List<Weekday> getAllAsPageByPeriodId(Long periodId, int page, int total);

    int getPages(Long periodId, double total);
}

