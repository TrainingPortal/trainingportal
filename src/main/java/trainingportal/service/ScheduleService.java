package trainingportal.service;

import trainingportal.model.Schedule;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface ScheduleService extends GenericService<Schedule> {

    List<Schedule> getAllAsPageByGroupId(Long scheduleGroupId, int page, int total);

    int countAllByGroupId(Long scheduleGroupId);

    int getPages(Long scheduleId, double total);

}
