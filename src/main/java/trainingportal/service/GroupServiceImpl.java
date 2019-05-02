package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.GroupDAOImpl;
import trainingportal.model.CourseStatus;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;

import java.util.List;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAOImpl groupDAO;

    @Override
    public List<Group> GroupsList() {
        return groupDAO.GroupsList();
    }

    @Override
    public Group findGroupById(Long GroupId) {
        return groupDAO.findGroupById(GroupId);
    }

    @Override
    public void saveGroup(Group group) {
        groupDAO.saveGroup(group);
    }

    @Override
    public void editGroup(Group group) {
        Group groupEdit = groupDAO.findGroupById(group.getGroupId());
        if (groupEdit != null) {
            groupEdit.setGroupName(group.getGroupName());
            groupEdit.setGroupCapacity(group.getGroupCapacity());
            groupEdit.setCourseId(group.getCourseId());
            groupEdit.setStatusId(group.getStatusId());
        }
        groupDAO.editGroup(groupEdit);
    }

    @Override
    public void deleteGroupById(Long GroupId) {
        groupDAO.deleteGroupById(GroupId);
    }

    @Override
    public List<GroupStatus> getStatusList() {
        return groupDAO.getStatusList();
    }
}
