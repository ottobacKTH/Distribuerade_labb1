package main.DB;

import main.BO.UserBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public UserBO getUser(UserBO userBO)
    {
        UserDBO user = new UserDBO(userBO.getUsername(), userBO.getPassword(),"");
        try
        {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, user.getUsername());
            pStatement.setString(2,user.getPassword());
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
    public List<UserBO> getUsers() {
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

    public int addUser(UserBO userBO) {
        UserDBO user = BOtoDBO(userBO);
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO user (username, password, role) VALUES (?,?,?)";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, user.getUsername());
            pStatement.setString(2, user.getPassword());
            pStatement.setString(3, user.getRole());
            int affectedRows = pStatement.executeUpdate();
            return affectedRows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int removeUser(UserBO userBO) {
        UserDBO user = BOtoDBO(userBO);
        try {
            Connection connection = DBManager.getConnection();
            String sql = "DELETE FROM user WHERE username = ? AND password = ? AND role = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, user.getUsername());
            pStatement.setString(2, user.getPassword());
            pStatement.setString(3, user.getRole());
            int affectedRows = pStatement.executeUpdate();
            return affectedRows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    private UserDBO BOtoDBO(UserBO BO)
    {
        return new UserDBO(BO.getUsername(), BO.getPassword(), BO.getRoleStr());
    }
    private UserBO DBOtoBO(UserDBO DBO) { return new UserBO(DBO.getUsername(), DBO.getPassword(), DBO.getRole()); }
}
