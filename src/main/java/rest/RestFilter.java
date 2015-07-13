package rest;

import authentication.BasicAuthenticationService;
import authentication.BasicSecurityContext;
import authentication.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Filtering all incoming requests.
 */
@Provider
@PreMatching
public class RestFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_HEADER = "Authorization";
    private @Context HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext containerRequest) throws WebApplicationException {
        //authentication
        HttpSession session = request.getSession(true);

        String authCredentials = containerRequest.getHeaderString(AUTHENTICATION_HEADER);
        BasicAuthenticationService authenticationService = new BasicAuthenticationService();
        User user = authenticationService.authenticate(authCredentials, session);

        if (user == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        //authorization
        containerRequest.setSecurityContext(new BasicSecurityContext(user));
    }
}
