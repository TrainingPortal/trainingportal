package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.GroupDAOImpl;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;

@Service("groupService")
@Transactional
public class GroupServiceImpl extends GenericServiceImpl<Group> implements GroupService {

    @Autowired
    private GroupDAOImpl groupDAO;

    @Override
    public void update(Group group) {
        Group groupEdit = groupDAO.findById(group.getGroupId());
        if (groupEdit != null) {
            groupEdit.setGroupName(group.getGroupName());
            groupEdit.setGroupCapacity(group.getGroupCapacity());
            groupEdit.setCourseId(group.getCourseId());
            groupEdit.setStatusId(group.getStatusId());
        }
        groupDAO.update(groupEdit);
    }

    @Override
    public List<Group> findAllByCourseId(Long id) {
        return groupDAO.findAllByCourseId(id);
    }

    @Override
    public List<GroupStatus> getStatusList() {
        return groupDAO.getStatusList();
    }

    @Override
    public GroupStatus findStatusById(Long id) {
        return groupDAO.findStatusById(id);
    }

    @Override
    public int getPages(double total) {
        return (int) Math.ceil(groupDAO.countAll() / total);
    }
}
