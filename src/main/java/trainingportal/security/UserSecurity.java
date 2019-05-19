package trainingportal.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import trainingportal.model.LoggedInUser;

import java.util.Collection;

public interface UserSecurity {
    boolean hasUserId(Long userId);

    boolean isConnectedWithTrainerByCourseId(Long courseId);

    boolean isConnectedWithTrainerByLessonId(Long lessonId);

    boolean isSubordinate(Long userId);

    boolean isConnectedWithLessonByCourseId(Long courseId);

    boolean isConnectedWithLessonByLessonId(Long lessonId);

    default Long getLoggedInUserId(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ((LoggedInUser)auth.getPrincipal()).getId();
    }

    default String getLoggedInUserRole(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Collection<GrantedAuthority> authority = ((LoggedInUser) auth.getPrincipal()).getAuthorities();

        return authority.iterator().next().toString();
    }
}
