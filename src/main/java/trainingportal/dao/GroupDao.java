package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;

import java.util.List;

public interface GroupDao extends GenericDao<Group> {
    List<Group> GroupsList();

    List<Group> getAllAsPageByCourseId(Long courseId, int page, int rowsPerPage);

    Group findGroupById(Long groupId);

    void saveGroup(Group group);

    void editGroup(Group group);

    void deleteGroupById(Long groupId);

    List<GroupStatus> getStatusList();

    GroupStatus findStatusById(Long id);

    List<Group> findAllByCourseId(Long id);

    Long getTrainerIdByGroupId(Long groupId);

    List<Group> getUserGroupsAsPageByCourseIdAndUserId(Long courseId, Long userId, int page, int rowsPerPage);

    int countAllByCourseId(Long courseId);

    int countGroupsByCourseIdAndUserId(Long courseId, Long userId);
}
