package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.ScheduleDao;
import trainingportal.model.Schedule;

import java.util.List;


@Service("scheduleService")
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public Schedule findById(Long scheduleId) {
        return scheduleDao.findById(scheduleId);
    }

    @Override
    public void save(Schedule schedule) {
        scheduleDao.save(schedule);

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
    public void deleteById(Long scheduleId) {
        scheduleDao.deleteById(scheduleId);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleDao.findAll();
    }

    @Override
    public List<Schedule> getAllAsPage(int page, int total) {
        return null;
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

//    @Override
//    public boolean isConnectedWithGroup(Long scheduleId, Long scheduleGroupId) {
//        Long scheduleGroupId =  scheduleDao.getScheduleIdByGroupId(scheduleId);
//
//        if(scheduleId == scheduleGroupId){
//            return true;
//        } else  {
//            return false;
//        }

}
