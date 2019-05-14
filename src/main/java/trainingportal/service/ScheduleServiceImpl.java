package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.dao.ScheduleDao;
import trainingportal.model.Schedule;
import trainingportal.service.generic.GenericServiceImpl;

@Service
public class ScheduleServiceImpl extends GenericServiceImpl<Schedule> implements ScheduleService {
    @Autowired
    ScheduleDao scheduleDao;

    @Override
    public Schedule findByGroupId(Long id) {
        return scheduleDao.findByGroupId(id);
    }
}
