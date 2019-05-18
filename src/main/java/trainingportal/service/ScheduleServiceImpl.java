package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.dao.ScheduleDao;
import trainingportal.model.Schedule;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;

@Service
public class ScheduleServiceImpl extends GenericServiceImpl<Schedule> implements ScheduleService {
    @Autowired
    ScheduleDao scheduleDao;

    @Override
    public Schedule findByGroupId(Long id) {
        return scheduleDao.findByGroupId(id);
    }

    @Override
    public void update(Schedule schedule) {
        Schedule scheduleEdit = scheduleDao.findById(schedule.getScheduleId());
        if( scheduleEdit != null){
            scheduleEdit.setScheduleGroupId(schedule.getScheduleGroupId());
            scheduleEdit.setScheduleDate(schedule.getScheduleDate());
            scheduleEdit.setScheduleLessonId(schedule.getScheduleLessonId());
        }
        scheduleDao.update(scheduleEdit);
    }

    @Override
    public int getNumberOfPages(List<Schedule> users, double total) {
        return 0;
    }

    @Override
    public List<Schedule> getAllAsPageByGroupId(Long scheduleGroupId, int page, int total) {

        if(page == 1){

        }else{
            page = (page - 1)*total + 1;
        }
        return scheduleDao.getAllAsPageByGroupId(scheduleGroupId,page,total);
    }

    @Override
    public int countAllByGroupId(Long scheduleGroupId) {
        return scheduleDao.countAllByGroupIdId(scheduleGroupId);
    }

    @Override
    public int getPages(Long scheduleId, double total) {
        return (int) Math.ceil(scheduleDao.countAllByGroupIdId(scheduleId)/total);
    }
}
