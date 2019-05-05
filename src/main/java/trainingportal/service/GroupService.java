package trainingportal.service;

import org.apache.poi.ss.formula.functions.T;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;

import java.util.List;

public interface GroupService {
    List<Group> GroupsList();

    Group findGroupById(Long GroupId);

    void saveGroup(Group group);

    void editGroup(Group group);

    void deleteGroupById(Long GroupId);

    List<GroupStatus> getStatusList();

    GroupStatus findStatusById(Long id);

    int getPages(double total);

    List<Group> getAllAsPage(int page, int total);
}
