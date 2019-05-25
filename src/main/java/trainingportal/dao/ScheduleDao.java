package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Schedule;

import java.util.List;

public interface ScheduleDao extends GenericDao<Schedule> {
    List<Schedule> findAllByGroupId(Long id);

    List<Schedule> getAllAsPageByGroupId(Long scheduleGroupId, int page, int total);

    int countAllByGroupId(Long scheduleGroupId);
}
