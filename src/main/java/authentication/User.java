package authentication;

import java.security.Principal;

/**
 * Created by root on 10.07.2015.
 */
public class User implements Principal {

    private String name;
    private String password;
    private String role;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean authenticate() {
        boolean result = false;

        if (name.equals("admin") && password.equals("admin")) {
            role = "ADMIN";
            result = true;
        }

        return result;
    }
}
