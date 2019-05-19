package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainingportal.dao.GroupDao;
import trainingportal.dao.LessonDao;
import trainingportal.dao.ScheduleDao;
import trainingportal.model.Schedule;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;

@Service
public class ScheduleServiceImpl extends GenericServiceImpl<Schedule> implements ScheduleService {
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private LessonDao lessonDao;

    @Override
    public List<Schedule> findAllByGroupId(Long id) {
        return scheduleDao.findAllByGroupId(id);
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
    public List<Schedule> getAllAsPageByGroupId(Long scheduleGroupId, int page, int total) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

        return scheduleDao.getAllAsPageByGroupId(scheduleGroupId,page,total);
    }

    @Override
    public int countAllByGroupId(Long scheduleGroupId) {
        return scheduleDao.countAllByGroupId(scheduleGroupId);
    }

    @Override
    public int getPages(Long scheduleId, double total) {
        return (int) Math.ceil(scheduleDao.countAllByGroupId(scheduleId)/total);
    }

    @Override
    public List<Schedule> getSchedules(Long id) {
        List<Schedule> schedules = findAllByGroupId(id);
        for(Schedule schedule:schedules){
            schedule.setScheduleGroup(groupDao.findById(schedule.getScheduleGroupId()));
            schedule.setScheduleLesson(lessonDao.findById(schedule.getScheduleLessonId()));
        }
        return schedules;
    }
}
