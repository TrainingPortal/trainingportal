package trainingportal.service;

import org.springframework.security.core.Authentication;
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

    int getPages(Long courseId,double total);

    List<Group> getAllAsPageByCourseId(Long courseId, int page, int total);

    List<Group> getUserGroupsAsPageByCourseIdAndUserId(Long courseId, Long userId, int page, int total);

    List<Group> getAllAsPage(int page, int total);

    boolean isTrainerConnectedWithGroup(Long trainerId, Long groupId);

    List<Group> getGroupsPage(Long courseId, int page, int total, Long userId, String role);
}
