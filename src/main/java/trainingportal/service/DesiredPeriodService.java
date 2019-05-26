package trainingportal.service;

import trainingportal.model.DesiredPeriod;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface DesiredPeriodService extends GenericService<DesiredPeriod> {

    int getNumberOfPages(List<DesiredPeriod> users, double rowsPerPage);

    List<DesiredPeriod> getPeriodCourseId(Long courseId);

    List<DesiredPeriod> getPeriodPageByCourseId(int page, int rowsPerPage, Long courseId);

    int getPages(Long courseId, double rowsPerPage);

    boolean isConnectedWithTrainer(Long userId, Long courseId);

    boolean isConnectedWithPeriodByCourseId(Long userId, Long courseId);
}

