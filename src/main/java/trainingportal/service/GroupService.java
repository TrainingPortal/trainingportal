package trainingportal.service;

import trainingportal.model.Group;
import trainingportal.model.GroupStatus;
import trainingportal.service.generic.GenericService;

import java.util.List;

public interface GroupService extends GenericService<Group> {
    List<Group> findAllByCourseId(Long id);

    List<GroupStatus> getStatusList();

    GroupStatus findStatusById(Long id);

    int getPages(Long courseId,double rowsPerPage);

    List<Group> getAllAsPageByCourseId(Long courseId, int page, int rowsPerPage);

    List<Group> getUserGroupsAsPageByCourseIdAndUserId(Long courseId, Long userId, int page, int rowsPerPage);

    List<Group> getAllAsPage(int page, int rowsPerPage);

    boolean isTrainerConnectedWithGroup(Long trainerId, Long groupId);

    List<Group> getGroupsPage(Long courseId, int page, int rowsPerPage, Long userId, String role);

    boolean isConnectedWithTrainerByGroupId(Long trainerId, Long groupId);

    boolean isConnectedWithUserByGroupId(Long userId, Long groupId);

    void deleteFromUserGroupByUserIdAndGroupId(Long userId, Long groupId);
}
