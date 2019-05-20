package trainingportal.service;

import trainingportal.model.DesiredPeriod;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface DesiredPeriodService extends GenericService<DesiredPeriod> {

    int getNumberOfPages(List<DesiredPeriod> users, double total);

    List<DesiredPeriod> getPeriodCourseId(Long courseId);

    List<DesiredPeriod> getPeriodPageByCourseId(int page, int total, Long courseId);

    int getPages(Long courseId, double total);

    boolean isConnectedWithTrainer(Long userId, Long courseId);

    boolean isConnectedWithPeriodByCourseId(Long userId, Long courseId);
}

