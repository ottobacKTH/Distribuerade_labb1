package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.util.ArrayList;
import java.util.List;

public class MockUserService implements UserDatabaseService{

    // test users: vincent, greg, ragnar
    @Override
    public UserBO getUser(UserBO userBO) {
        return new UserBO(userBO.getUsername(), userBO.getPassword(),"customer");
    }

    @Override
    public List<UserBO> getUsers() {
        List<UserBO> list = new ArrayList<>();
        list.add(new UserBO("vincent","password", "customer"));
        list.add(new UserBO("greg","password123", "staff"));
        list.add(new UserBO("ragnar","password123", "admin"));
        return list;
    }

    @Override
    public int addUser(UserBO userBO) {
        if(userBO.getUsername().equals("ragnar"))
        {
            return 0;
        }
        return 1;
    }

    @Override
    public int removeUser(UserBO userBO) {
        if(userBO.getUsername().equals("ragnar"))
        {
            return 0;
        }
        return 1;
    }
}
