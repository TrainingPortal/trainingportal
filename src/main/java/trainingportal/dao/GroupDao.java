package trainingportal.dao;

import trainingportal.dao.generic.GenericDao;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;

import java.util.List;

public interface GroupDao extends GenericDao<Group> {

    List<GroupStatus> getStatusList();

    GroupStatus findStatusById(Long id);

    List<Group> findAllByCourseId(Long id);
}
