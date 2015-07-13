package authentication;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

/**
 * Checking user role.
 */
public class BasicSecurityContext implements SecurityContext {

    private User user;
    @Inject
    javax.inject.Provider<UriInfo> uriInfo;

    public BasicSecurityContext(User user) {
        this.user = user;
        System.out.println("BasicSecurityContext");
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return s.equals(user.getRole());
    }

    @Override
    public boolean isSecure() {
        return "https".equals(uriInfo.get().getRequestUri().getScheme());
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
