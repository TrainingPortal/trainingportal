package trainingportal.service;

import trainingportal.model.Weekday;
import trainingportal.service.generic.GenericService;

import java.util.List;


public interface WeekdayService extends GenericService<Weekday> {

    int getNumberOfPages(List<Weekday> users, double rowsPerPage);

    List<Weekday> getWeekdayPeriodId(Long periodId);

    List<Weekday> getAllAsPageByPeriodId(Long periodId, int page, int rowsPerPage);

    int getPages(Long periodId, double rowsPerPage);
}

