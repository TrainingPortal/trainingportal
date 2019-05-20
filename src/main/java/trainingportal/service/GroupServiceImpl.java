package trainingportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainingportal.dao.CourseDao;
import trainingportal.dao.GroupDao;
import trainingportal.dao.UserGroupDao;
import trainingportal.model.Group;
import trainingportal.model.GroupStatus;
import trainingportal.model.UserGroup;
import trainingportal.service.generic.GenericServiceImpl;

import java.util.List;

@Service("groupService")
@Transactional
public class GroupServiceImpl extends GenericServiceImpl<Group> implements GroupService {
    @Autowired
    private GroupDao groupDAO;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private UserGroupDao userGroupDao;

    @Override
    public List<Group> getAllAsPageByCourseId(Long courseId, int page, int total) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

        return groupDAO.getAllAsPageByCourseId(courseId, page, total);
    }

    @Override
    public List<Group> getUserGroupsAsPageByCourseIdAndUserId(Long courseId, Long userId, int page, int total) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

        return groupDAO.getUserGroupsAsPageByCourseIdAndUserId(courseId, userId, page, total);
    }

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
    public int getPages(Long courseId,double total) {
        return (int) Math.ceil(groupDAO.countAll() / total);
    }

    @Override
    public boolean isTrainerConnectedWithGroup(Long trainerId, Long groupId) {

        Long userId =  groupDAO.getTrainerIdByGroupId(groupId);

        return userId.equals(trainerId);
    }

    @Override
    public List<Group> getGroupsPage(Long courseId, int page, int total, Long userId, String role) {

        //get page number in GENERIC SERVICE implementation class
        page = getPageNumber(page,total);

        List<Group> groupList;

        if(role.equals("ROLE_EMPLOYEE") || role.equals("ROLE_MANAGER")) {
            groupList = groupDAO.getUserGroupsAsPageByCourseIdAndUserId(courseId, userId, page, total);
        } else {
            groupList = groupDAO.getAllAsPageByCourseId(courseId, page, total);
        }

        for(Group group : groupList){
            group.setCourse(courseDao.findById(group.getCourseId()));
            group.setStatus(groupDAO.findStatusById(group.getStatusId()));
        }

        return groupList;
    }

    @Override
    public boolean isConnectedWithTrainerByGroupId(Long trainerId, Long groupId) {

        Long userId =  groupDAO.getTrainerIdByGroupId(groupId);

        return userId.equals(trainerId);
    }

    @Override
    public boolean isConnectedWithUserByGroupId(Long userId, Long groupId) {

        List<UserGroup> users =  userGroupDao.getUserGroupListByGroupId(groupId);

        for(UserGroup user : users){
            if(user.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteFromUserGroupByUserIdAndGroupId(Long userId, Long groupId) {
        userGroupDao.deleteFromUserGroupByUserIdAndGroupId(userId, groupId);
    }
}
