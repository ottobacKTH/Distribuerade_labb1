/**
 * Authors; Otto & Habib
 */
package main.DT;

import java.util.Objects;

/**
 * A DT representation of the user
 */
public class UserDTO {
    private String username;
    private String password;
    private String role;

    /**
     * a full constructor encompassing every field in the class
     * @param username the users username
     * @param password the users password
     * @param role the role of the user
     */
    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * A constructor where the users role is omitted
     * @param username the users username
     * @param password the users password
     */
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * An empty constructor
     */
    public UserDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (!Objects.equals(username, userDTO.username)) return false;
        if (!Objects.equals(password, userDTO.password)) return false;
        return Objects.equals(role, userDTO.role);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    /**
     * Get the username of the user
     * @return the users username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Assign the user a username
     * @param username the username to be assigned
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the users password
     * @return the password to be retrieved
     */
    public String getPassword() {
        return password;
    }

    /**
     * assign the users password
     * @param password the password to be assigned
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the role of the user
     * @return the users role
     */
    public String getRole() {
        return role;
    }

    /**
     * Assign the role of the user
     * @param role The role to be assigned
     */
    public void setRole(String role) {
        this.role = role;
    }
}
