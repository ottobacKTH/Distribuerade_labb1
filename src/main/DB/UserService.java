package main.DB;

import main.BO.UserBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public static UserBO getUser(UserBO loginUser)
    {
        try
        {
            UserDBO user = BOtoDBO(loginUser);
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, loginUser.getUsername());
            pStatement.setString(2,loginUser.getPassword());
            ResultSet result = pStatement.executeQuery();
            if(result.next())
            {
                UserDBO userFromDB = new UserDBO(result.getString("username"),
                        result.getString("password"),
                        result.getString("role"));
                return DBOtoBO(userFromDB);
            }
            else {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static List<UserBO> getUsers() {
        ArrayList<UserBO> list = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from user");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                list.add(new UserBO(username, password, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static UserBO addUser(String username, String password, String role) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO user (username, password, role) VALUES (?,?,?)";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, username);
            pStatement.setString(2, password);
            pStatement.setString(3, role);
            pStatement.execute();
            UserDBO userFromDB = new UserDBO(username, password, role);
            return DBOtoBO(userFromDB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeUser(String username) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "DELETE FROM user WHERE username = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, username);
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static UserDBO BOtoDBO(UserBO BO)
    {
        return new UserDBO(BO.getUsername(), BO.getPassword(), BO.getRole());
    }
    private static UserBO DBOtoBO(UserDBO DBO)
    {
        return new UserBO(DBO.getUsername(), DBO.getPassword(), DBO.getRole());
    }
}
