package trainingportal.dao;

import trainingportal.model.Role;

import java.util.List;

public interface RoleDao {

    List<String> getRoleNames(Long userId);

    List<Role> getRoles();

}
