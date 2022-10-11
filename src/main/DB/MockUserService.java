/**
 * Authors; Otto & Habib
 */
package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.util.ArrayList;
import java.util.List;

public class MockUserService implements UserDatabaseService{

    // test users: vincent, greg, ragnar

    /**
     * Simulates getting a user with username and password
     * @param userBO BO representation of the user
     * @return a BO representation of the user with an appended role
     */
    @Override
    public UserBO getUser(UserBO userBO) {
        return new UserBO(userBO.getUsername(), userBO.getPassword(),"customer");
    }

    /**
     * Gets all the users
     * @return list of BO representation of vincent, greg and ragnar
     */
    @Override
    public List<UserBO> getUsers() {
        List<UserBO> list = new ArrayList<>();
        list.add(new UserBO("vincent","password", "customer"));
        list.add(new UserBO("greg","password123", "staff"));
        list.add(new UserBO("ragnar","password123", "admin"));
        return list;
    }

    /**
     * Simulates adding a user
     * @param userBO BO representation of the user to be added
     * @return 0 if user is ragnar, otherwise 1
     */
    @Override
    public int addUser(UserBO userBO) {
        if(userBO.getUsername().equals("ragnar"))
        {
            return 0;
        }
        return 1;
    }

    /**
     * simulates removing a user
     * @param userBO BO representation of the user to be removed
     * @return 0 if user is ragnar, otherwise 1
     */
    @Override
    public int removeUser(UserBO userBO) {
        if(userBO.getUsername().equals("ragnar"))
        {
            return 0;
        }
        return 1;
    }
}
