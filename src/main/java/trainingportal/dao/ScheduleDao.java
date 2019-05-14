package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Schedule;

import java.util.List;

public interface ScheduleDao extends GenericDao<Schedule> {

    List<Schedule> getAllAsPageByGroupId(Long scheduleGroupId, int page, int total);

    int countAllByGroupIdId(Long scheduleGroupId);

}
