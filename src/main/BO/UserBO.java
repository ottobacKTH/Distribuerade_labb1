package main.BO;

import java.util.Objects;

public class UserBO {

    private final String username;
    private String password;
    private Role role;

    public UserBO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = Role.valueOf(role.toUpperCase());
    }
    public UserBO(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

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

    public String getRoleStr() {
        return role.name().toLowerCase();
    }

    public Role getRoleEnum(){ return role; };

    public void setRoleStr(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    public void setRoleEnum(Role role) {this.role = role;}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
