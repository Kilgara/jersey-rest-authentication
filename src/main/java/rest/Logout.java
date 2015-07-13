package rest;

import authentication.User;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Removing user data from session.
 */
@Path("/logout")
@RolesAllowed({"ADMIN", "USER"})
public class Logout {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest req) {
        HttpSession session = req.getSession(true);

        User user = (User) session.getAttribute("User");
        if (user != null) {
            session.removeAttribute("User");
        }

        return Response.status(200).build();
    }

}
