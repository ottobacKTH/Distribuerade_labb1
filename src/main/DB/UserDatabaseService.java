package main.DB;

import main.BO.UserBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface UserDatabaseService {
    /**
     * make a search for a specific user, checks if it exists in the system
     * @param userBO BO representation of the user
     * @return the user that was found
     */
    public UserBO getUser(UserBO userBO);

    /**
     * Gets all the users in the system
     * @return a list of BO represenations of the users
     */
    public List<UserBO> getUsers();

    /**
     * Adds a user to the system
     * @param userBO BO representation of the user to be added
     * @return how many rows were affected
     */
    public int addUser(UserBO userBO);

    /**
     * Remove a specific user from the system
     * @param userBO BO representation of the user to be removed
     * @return how many rows were affected
     */
    public int removeUser(UserBO userBO);
}
