package trainingportal.service;

import trainingportal.model.Weekday;
import trainingportal.service.generic.GenericService;

import java.util.List;


public interface WeekdayService extends GenericService<Weekday> {

    int getNumberOfPages(List<Weekday> users, double total);

    List<Weekday> getWeekdayPeriodId(Long desiredPeriodId);

    List<Weekday> getAllAsPageByPeriodId(Long desiredPeriodId, int page, int total);

    int getPages(Long desiredPeriodId, double total);
}

