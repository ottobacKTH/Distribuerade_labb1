package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for handling user in the database.
 * This class access database via DBManger class by sending SQL statements,
 * Will use DBO objects to communicate with BO layer
 */
public class UserService implements UserDatabaseService{


    /**
     * @param userBO BO representation of the user
     * User BO representation converted to DBO representation to handle with database
     * Gets a specific user from the database
     * @return the converted user representation from DBO to BO (or null if failed)
     */
    @Override
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

    /**
     * Gets all users from the database
     * @return a list with all users with BO representation
     */
    @Override
    public List<UserBO> getUsers() {
        ArrayList<UserDBO> list = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from user");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                list.add(new UserDBO(username, password, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<UserBO> BOlist = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            BOlist.add(DBOtoBO(list.get(i)));
        }
        return BOlist;
    }

    /**
     * @param userBO BO representation of the user
     * User BO representation converted to DBO representation to handle with database
     * Adds a user to the database
     * @return the number of affected rows (or 0 if failed)
     */
    @Override
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

    /**
     * @param userBO BO representation of the user
     * User BO representation converted to DBO representation to handle with database
     * Removes a user to the database
     * @return the number of affected rows (or 0 if failed)
     */
    @Override
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

    /**
     * @param BO the BO representation of user
     * Converts the representation of user from BO to DBO
     * @return the DBO representation of user
     */
    private UserDBO BOtoDBO(UserBO BO)
    {
        return new UserDBO(BO.getUsername(), BO.getPassword(), BO.getRoleStr());
    }

    /**
     * @param DBO the DBO representation of user
     * Converts the representation of user from DBO to BO
     * @return the BO representation of user
     */
    private UserBO DBOtoBO(UserDBO DBO) { return new UserBO(DBO.getUsername(), DBO.getPassword(), DBO.getRole()); }
}
