package trainingportal.service;

import trainingportal.model.Schedule;
import trainingportal.service.generic.GenericService;

public interface ScheduleService extends GenericService<Schedule> {
    Schedule findByGroupId(Long id);
}
