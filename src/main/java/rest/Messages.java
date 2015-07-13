package rest;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by root on 11.07.2015.
 */
@Path("/messages")
@RolesAllowed({"ADMIN", "USER"})
public class Messages {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response main(@Context HttpServletRequest req) {
        HttpSession session = req.getSession(true);

        HashMap<String, Object> message1 = new HashMap<String, Object>();
        message1.put("title", "mes: 1 - test title");
        message1.put("message", "mes: 1 - test message");
        message1.put("author", "mes: 1 - test author");

        HashMap<String, Object> message2 = new HashMap<String, Object>();
        message2.put("title", "mes: 2 - test title new");
        message2.put("message", "mes: 2 - test message");
        message2.put("author", "mes: 2 - test author");

        HashSet<Object> messages = new HashSet<Object>();
        messages.add(message1);
        messages.add(message2);

        return Response.status(200).entity(messages).build();
    }
}
