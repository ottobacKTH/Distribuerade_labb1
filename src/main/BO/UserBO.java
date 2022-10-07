package main.BO;

import java.util.Objects;

public class UserBO {

    private final String username;
    private String password;
    private String role;

    public UserBO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
