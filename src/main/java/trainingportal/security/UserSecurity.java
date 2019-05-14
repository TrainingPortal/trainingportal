package trainingportal.security;

import org.springframework.security.core.Authentication;

public interface UserSecurity {
    boolean hasUserId(Authentication authentication, Long userId);

    boolean isConnectedWithTrainer(Authentication authentication, Long courseId);

    boolean isSubordinate(Authentication authentication, Long userId);
}
