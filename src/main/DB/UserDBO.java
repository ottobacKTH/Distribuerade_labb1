package main.DB;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDBO {

    private String username;

    private String password;
    private String role;

    private List<ItemDBO> items;

    public UserDBO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        items = new ArrayList<>();
    }

    public UserDBO(String username, String password, String role, List<ItemDBO> items) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.items = new ArrayList<>();
        for(int i = 0; i < items.size(); i++)
        {
            this.items.add(items.get(i));
        }
    }

    public UserDBO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDBO userDBO = (UserDBO) o;

        if (!Objects.equals(username, userDBO.username)) return false;
        if (!Objects.equals(password, userDBO.password)) return false;
        if (!Objects.equals(role, userDBO.role)) return false;
        return Objects.equals(items, userDBO.items);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ItemDBO> getItems() {
        List<ItemDBO> returnList = new ArrayList<>();
        for(int i = 0; i < items.size(); i++)
        {
            returnList.add(items.get(i));
        }
        return returnList;
    }

    public void setItems(List<ItemDBO> items) {
        for(int i  = 0; i < items.size(); i++)
        {
            this.items.add(items.get(i));
        }
    }
}
