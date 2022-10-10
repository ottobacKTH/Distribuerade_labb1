package main.DB;

import main.BO.UserBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface UserDatabaseService {
    public UserBO getUser(UserBO userBO);
    public List<UserBO> getUsers();
    public int addUser(UserBO userBO);
    public int removeUser(UserBO userBO);
}
