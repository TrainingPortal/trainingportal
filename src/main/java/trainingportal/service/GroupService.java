package trainingportal.service;

import trainingportal.model.Group;
import trainingportal.model.GroupStatus;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface GroupService extends GenericService<Group> {
    List<Group> findAllByCourseId(Long id);

    List<GroupStatus> getStatusList();

    GroupStatus findStatusById(Long id);

    int getPages(double total);

    List<Group> getAllAsPage(int page, int total);
}
