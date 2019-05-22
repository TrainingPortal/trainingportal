package trainingportal.dao;

import trainingportal.model.DesiredPeriod;

import java.util.List;


public interface DesiredPeriodDao {


    List<DesiredPeriod> findAll();

    List<DesiredPeriod> getAllAsPageByCourseId(Long courseId, int page, int total);

    List<DesiredPeriod> getPeriodCourseId(Long courseId);

    DesiredPeriod findById(Long desiredPeriodId);

    void save(DesiredPeriod desiredPeriod);

    void update(DesiredPeriod desiredPeriod);

    void deleteById(Long desiredPeriodId);

    List<DesiredPeriod> getAllAsPageByUserId(Long userId, Long courseId, int page, int total);

    int countAllByCourseId(Long courseId);

    Long getUserIdByCourseId(Long id);
}
