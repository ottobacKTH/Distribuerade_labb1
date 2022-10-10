package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.util.ArrayList;
import java.util.List;

public class MockItemService implements ItemDatabaseService{

    // test users: vincent, greg, ragnar
    // ragnar och krikon misslyckas
    @Override
    public List<ItemBO> getStoreItems() {
        List<ItemBO> list = new ArrayList<ItemBO>();
        list.add(new ItemBO(0,"apple",10,42));
        list.add(new ItemBO(1,"pear",20,60));
        list.add(new ItemBO(2,"orange",4,3));
        return list;
    }

    @Override
    public List<ItemBO> getCartItems(UserBO userBO) {
        List<ItemBO> list = new ArrayList<ItemBO>();
        if(userBO.getUsername().equals("vincent"))
        {
            list.add(new ItemBO(0,"apple",10,2));
            list.add(new ItemBO(1,"pear",20,10));
        }
        else if(userBO.getUsername().equals("greg"))
        {
            list.add(new ItemBO(0,"apple",10,42));
            list.add(new ItemBO(1,"pear",20,60));
            list.add(new ItemBO(2,"orange",4,3));
        }
        return list;
    }

    @Override
    public int addNewItemToStore(ItemBO itemBO) {
        if(itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean makePurchase(UserBO userBO) {
        if(userBO.getUsername().equals("vincent"))
        {
            return true;
        }
        else if(userBO.getUsername().equals("greg"))
        {
            return false;
        }
        else {
            throw new IllegalStateException("no cart");
        }
    }

    @Override
    public int removeItemFromStore(ItemBO itemBO) {
        if(itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    @Override
    public int changeAmountFromStore(ItemBO itemBO) {
        if(itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    @Override
    public int addItemToCart(ItemBO itemBO, UserBO userBO) {
        if(userBO.getUsername().equals("ragnar") && itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    @Override
    public int changeItemAmountFromCart(UserBO userBO, ItemBO itemBO) {
        if(itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    @Override
    public int removeItemFromCart(UserBO userBO, ItemBO itemBO) {
        if(userBO.getUsername().equals("ragnar") && itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }
}
