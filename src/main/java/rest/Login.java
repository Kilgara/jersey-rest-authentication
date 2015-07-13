package rest;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * Sending session id to user
 */
@Path("/login")
//@RolesAllowed("ADMIN")
//@PermitAll
public class Login {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest req) {
        HttpSession session = req.getSession(true);

        HashMap<String, Object> json = new HashMap<String, Object>();
        json.put("SessionId", session.getId());

        return Response.status(200).entity(json).build();
    }

}
