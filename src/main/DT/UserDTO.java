package main.DT;

import java.util.Objects;

public class UserDTO {
    private String userName;
    private String password;
    private String role;

    public UserDTO(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (!Objects.equals(userName, userDTO.userName)) return false;
        if (!Objects.equals(password, userDTO.password)) return false;
        return Objects.equals(role, userDTO.role);
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
