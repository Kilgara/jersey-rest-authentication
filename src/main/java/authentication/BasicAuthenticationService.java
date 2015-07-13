package authentication;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

/**
 * Authenticating user with Basic Authentication
 */
public class BasicAuthenticationService {
    //authCredentials - encoded user login and password. Example "Basic YWRtaW46YWRtaW4="
    public User authenticate(String authCredentials, HttpSession session) {

        if (authCredentials == null)
            return null;
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        //getting user data from session.
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("User");
            if (user != null) {
                if (!user.getName().equals(username) || !user.getPassword().equals(password)) {
                    return null;
                }
            }
        }
        //if session is empty.
        //authenticating user in database.
        if (user == null) {
            user = new User(username, password);
            if (!user.authenticate()) {
                return null;
            }

            session.setAttribute("User", user);
        }

        return user;
    }
}
