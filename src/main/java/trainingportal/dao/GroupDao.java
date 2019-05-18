package trainingportal.dao;

import trainingportal.model.Group;
import trainingportal.model.GroupStatus;

import java.util.List;

public interface GroupDao {

    List<Group> GroupsList();

    List<Group> getAllAsPageByCourseId(Long courseId, int page, int total);

    Group findGroupById(Long groupId);

    void saveGroup(Group group);

    void editGroup(Group group);

    void deleteGroupById(Long groupId);

    List<GroupStatus> getStatusList();

    GroupStatus findStatusById(Long id);

    int countAll();

    List<Group> getAllAsPage(int page, int total);

    Long getTrainerIdByGroupId(Long groupId);

    List<Group> getUserGroupsAsPageByCourseIdAndUserId(Long courseId, Long userId, int page, int total);
}
