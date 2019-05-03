package trainingportal.service;

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
}
