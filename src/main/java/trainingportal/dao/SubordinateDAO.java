package trainingportal.dao;

import trainingportal.model.User;

import java.util.List;

public interface SubordinateDAO {

    List<User> findSubordinatesById(Long id);

    User findManagerBySubordinateId(Long id);

    List<User> findFreeUsers();

    void setManagerId(Long managerId, Long userId);
}
