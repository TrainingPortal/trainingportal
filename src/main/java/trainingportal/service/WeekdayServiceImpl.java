package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.WeekdayDaoImpl;
import trainingportal.model.Course;
import trainingportal.model.Weekday;

import java.util.List;


@Service("weekdayService")
@Transactional
public class WeekdayServiceImpl implements WeekdayService {

    @Autowired
    private WeekdayDaoImpl WeekdayDao;

    @Override
    public int getNumberOfPages(List<Weekday> users, double total) {
        return 0;
    }

    @Override
    public Weekday findById(Long weekdayId) {
        return WeekdayDao.findById(weekdayId);
    }

    @Override
    public void save(Weekday weekday) {
        WeekdayDao.save(weekday);
    }

    @Override
    public void update(Weekday weekday) {
        Weekday weekdayEdit = WeekdayDao.findById(weekday.getWeekdayId());
        if (weekdayEdit != null) {
            weekdayEdit.setWeekdayId(weekday.getWeekdayId());
            weekdayEdit.setDayName(weekday.getDayName());
            weekdayEdit.setTimeStart(weekday.getTimeStart());
            weekdayEdit.setTimeEnd(weekday.getTimeEnd());
            weekdayEdit.setPeriodId(weekday.getPeriodId());


        }
        WeekdayDao.update(weekdayEdit);
    }


    @Override
    public void deleteById(Long weekdayId) {
        WeekdayDao.deleteById(weekdayId);
    }

    @Override
    public List<Weekday> findAll() {
        return WeekdayDao.findAll();
    }

    @Override
    public List<Weekday> getAllAsPage(int page, int total) {
        return null;
    }


    @Override
    public List<Weekday> getWeekdayPeriodId(Long periodId) {
        return WeekdayDao.getWeekdayPeriodId(periodId);
    }

    @Override
    public List<Weekday> getAllAsPageByPeriodId(Long periodId, int page, int total) {

        if (page == 1) {
            //do nothing
        } else {
            page = (page - 1) * total + 1;
        }
        List<Weekday> allAsPageByPeriodId = WeekdayDao.getAllAsPageByPeriodId(periodId, page, total);
        return allAsPageByPeriodId;
    }

    @Override
    public int getPages(Long periodId, double total) {
        return (int) Math.ceil(WeekdayDao.countAllByPeriodId(periodId) / total);
    }


}

