package main.BO;

import junit.framework.TestCase;
import main.DT.UserDTO;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class UserManagementTest extends TestCase {

    private UserManagement userManagement = new UserManagement();
    public void testGetUsers() {
        List<UserDTO> list = new ArrayList<>();
        list.add(new UserDTO("vincent","password", "customer"));
        list.add(new UserDTO("greg","password123", "staff"));
        list.add(new UserDTO("ragnar","password123", "admin"));
        List<UserDTO> gottenList = userManagement.getUsers();
        for(int i = 0; i < list.size(); i++)
        {
            Assert.assertEquals(list.get(i),gottenList.get(i));
        }
    }

    public void testLogin() {
        UserDTO user = new UserDTO();
        try
        {
            userManagement.login(user);
            fail("exception wasn't thrown");
        }
        catch (Exception e)
        {
            Assert.assertNotNull(e);
        }

        user.setUsername("vincent");
        try
        {
            userManagement.login(user);
            fail("exception wasn't thrown");
        }
        catch (Exception e)
        {
            Assert.assertNotNull(e);
        }

        user.setUsername(null);
        user.setPassword("password");
        try
        {
            userManagement.login(user);
            fail("exception wasn't thrown");
        }
        catch (Exception e)
        {
            Assert.assertNotNull(e);
        }

        user = new UserDTO("vincent","password","weirdRole");
        userManagement.login(user);
        user = new UserDTO("vincent","password");
    }

    public void testAddUser() {
        UserDTO user = new UserDTO();
        try
        {
            userManagement.addUser(user);
            fail("exception wasn't thrown");
        }
        catch (Exception e)
        {
            Assert.assertNotNull(e);
        }

        user = new UserDTO("vincent","password","weirdRole");
        try
        {
            userManagement.addUser(user);
            fail("exception wasn't thrown");
        }
        catch (Exception e)
        {
            Assert.assertNotNull(e);
        }

        user.setRole("customer");
        Assert.assertEquals(user, userManagement.addUser(user));

        user = new UserDTO("ragnar","password123","admin");
        Assert.assertEquals(null,userManagement.addUser(user));
    }

    public void testRemoveUser() {
    }
}