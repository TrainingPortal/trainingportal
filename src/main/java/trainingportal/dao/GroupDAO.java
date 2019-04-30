package trainingportal.dao;

import trainingportal.model.Group;

import java.util.List;

public interface GroupDao {

    List<Group> GroupsList();

    Group findGroupById(Long groupId);

    void saveGroup(Group group);

    void editGroup(Group group);

    void deleteGroupById(Long groupId);

}
