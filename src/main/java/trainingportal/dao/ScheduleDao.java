package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Schedule;

public interface ScheduleDao extends GenericDao<Schedule> {
    Schedule findByGroupId(Long id);
}
