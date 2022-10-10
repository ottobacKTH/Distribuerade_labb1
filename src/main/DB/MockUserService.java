package main.DB;

import main.BO.UserBO;

import java.util.List;

public class MockUserService implements UserDatabaseService{
    @Override
    public UserBO getUser(UserBO userBO) {
        return null;
    }

    @Override
    public List<UserBO> getUsers() {
        return null;
    }

    @Override
    public int addUser(UserBO userBO) {
        return 0;
    }

    @Override
    public int removeUser(UserBO userBO) {
        return 0;
    }
}
