package obrien.Project.three.utils;

import obrien.Project.three.entity.UserDetail;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Panashe Obrien Mugomba
 *
 *  */
public class SecurityUtils {

    public SecurityUtils() {
    }
    public static Optional<String> findFirstName() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetail) {
                        UserDetail springSecurityUser = (UserDetail) authentication.getPrincipal();
                        return springSecurityUser.getFirstName();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }
    public static Optional<Long> findUserByID() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetail) {
                        UserDetail springSecurityUser = (UserDetail) authentication.getPrincipal();
                        return springSecurityUser.userId();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (Long) authentication.getPrincipal();
                    }
                    return null;
                });
    }


    public static Optional<String> findUsername() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetail) {
                        UserDetail springSecurityUser = (UserDetail) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }

}
