package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.DesiredPeriodDao;
import trainingportal.dao.DesiredPeriodDaoImpl;
import trainingportal.dao.UserGroupDao;
import trainingportal.model.DesiredPeriod;
import trainingportal.model.UserGroup;

import java.util.List;
@Service
@Transactional
public  class DesiredPeriodServiceImpl implements DesiredPeriodService {

    @Autowired
    private DesiredPeriodDao desiredPeriodDao;

    @Autowired
    private UserGroupDao userGroupDao;

    @Override
    public List<DesiredPeriod> findAll() {
        return desiredPeriodDao.findAll();
    }

    @Override
    public DesiredPeriod findById(Long desiredPeriodId) {
        return desiredPeriodDao.findById(desiredPeriodId);
    }

    @Override
    public void save(DesiredPeriod desiredPeriod) {
        desiredPeriodDao.save(desiredPeriod);
    }

    @Override
    public void update(DesiredPeriod desiredPeriod) {
        DesiredPeriod periodEdit = desiredPeriodDao.findById(desiredPeriod.getDesiredPeriodId());
        if (periodEdit != null) {
            periodEdit.setUserId(desiredPeriod.getUserId());
            periodEdit.setCourseId(desiredPeriod.getCourseId());

        }
        desiredPeriodDao.update(periodEdit);
    }


    @Override
    public void deleteById(Long desiredPeriodId) {
        desiredPeriodDao.deleteById(desiredPeriodId);
    }

    @Override
    public List<DesiredPeriod> getAllAsPage(int page, int rowsPerPage) {
        return null;
    }

    @Override
    public int getNumberOfPages(List<DesiredPeriod> users, double rowsPerPage) {
        return 0;
    }


    @Override
    public List<DesiredPeriod> getPeriodCourseId(Long courseId) {
        return desiredPeriodDao.getPeriodCourseId(courseId);
    }

    @Override
    public List<DesiredPeriod> getPeriodPageByCourseId(int page, int rowsPerPage, Long courseId) {

        if(page == 1){
            //do nothing
        } else {
            page = (page - 1) * rowsPerPage + 1;
        }
        return desiredPeriodDao.getAllAsPageByCourseId(courseId, page, rowsPerPage);
    }

    @Override
    public int getPages(Long courseId, double rowsPerPage) {
        return (int) Math.ceil(desiredPeriodDao.countAllByCourseId(courseId) / rowsPerPage);
    }

    @Override
    public boolean isConnectedWithTrainer(Long userId, Long courseId) {
        Long trainerId =  desiredPeriodDao.getUserIdByCourseId(courseId);

        return userId == trainerId;
    }

    @Override
    public boolean isConnectedWithPeriodByCourseId(Long userId, Long courseId) {

        List<UserGroup> users =  userGroupDao.getUserIdByCourseId(courseId);

        for(UserGroup user : users){
            if(user.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }


}
