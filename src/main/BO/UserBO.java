/**
 * Authors; Otto & Habib
 */
package main.BO;

import java.util.Objects;

/**
 * Representation of user i the BO layer. This class is also responsible for which roles are allowed in the system
 */
public class UserBO {

    private final String username;
    private String password;
    private Role role;

    /**
     * Full constructor where role is assigned through a string value
     * @param username
     * @param password
     * @param role Only specific values of roles are allowed; customer, staff and admin (no case)
     */
    public UserBO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = Role.valueOf(role.toUpperCase());
    }

    /**
     * full constructor where role is assigned through the Role enum
     * @param username
     * @param password
     * @param role
     */
    public UserBO(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructor for user where role is omitted
     * @param username
     * @param password
     */
    public UserBO(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBO userBO = (UserBO) o;
        return username.equals(userBO.username) && password.equals(userBO.password) && role.equals(userBO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role);
    }

    /**
     * get the name of users role
     * @return name of role
     */
    public String getRoleStr() {
        return role.name().toLowerCase();
    }

    /**
     * get the full role of the user
     * @return Role enum
     */
    public Role getRoleEnum(){ return role; };

    /**
     * set role of the user using its name
     * @param role only specific values allowed: customer, staff, admin
     */
    public void setRoleStr(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    /**
     * set role using the Role enum
     * @param role
     */
    public void setRoleEnum(Role role) {this.role = role;}

    /**
     * get username of the user
     * @return users username
     */
    public String getUsername() {
        return username;
    }

    /**
     * get users password
     * @return users password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set users password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
