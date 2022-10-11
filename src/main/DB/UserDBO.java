/**
 * Authors; Otto & Habib
 */
package main.DB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * a DB representation of the user
 */
public class UserDBO {

    private String username;

    private String password;
    private String role;

    /**
     * full constructor for the user
     * @param username the users username
     * @param password the password of the user
     * @param role the users' role
     */
    public UserDBO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * An empty constructor, initiates nothing
     */
    public UserDBO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDBO userDBO)) return false;

        if (!Objects.equals(username, userDBO.username)) return false;
        if (!Objects.equals(password, userDBO.password)) return false;
        return Objects.equals(role, userDBO.role);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    /**
     * Get the role of the user
     * @return the users role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set the role of the user
     * @param role the role to be set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get the username of the user
     * @return the users username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user
     * @param username the username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the user
     * @return the users password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user
     * @param password the password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
