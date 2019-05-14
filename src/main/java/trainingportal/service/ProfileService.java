package trainingportal.service;

import trainingportal.model.User;

public interface ProfileService {

    User initManager(User user);

    String getReadableRole(User user);
}
