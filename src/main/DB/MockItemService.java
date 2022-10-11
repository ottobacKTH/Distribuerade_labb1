/**
 * Authors; Otto & Habib
 */
package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.util.ArrayList;
import java.util.List;

public class MockItemService implements ItemDatabaseService{

    // test users: vincent, greg, ragnar
    // ragnar och krikon misslyckas

    /**
     * Get all items in store. Returns apples, pears and oranges
     * @return List item
     */
    @Override
    public List<ItemBO> getStoreItems() {
        List<ItemBO> list = new ArrayList<ItemBO>();
        list.add(new ItemBO(0,"apple",10,42));
        list.add(new ItemBO(1,"pear",20,60));
        list.add(new ItemBO(2,"orange",4,3));
        return list;
    }

    /**
     * get the users cart. Vincent has apples and pears, Greg has apples pears and oranges and Ragnar has nothing
     * @param userBO a BO representation of the user
     * @return
     */
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

    /**
     * Simulates adding a new item to the store. Krikon can not be added
     * @param itemBO BO representation of the item
     * @return 0 for krikon, 1 otherwise
     */
    @Override
    public int addNewItemToStore(ItemBO itemBO) {
        if(itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    /**
     * Simulates purchasing the items in the cart. Throws exception if user is Ragnar
     * @param userBO A BO representation of the user
     * @return true if user is Vincent, false if user is Greg
     */
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

    /**
     * Simulates removing and item from the store
     * @param itemBO BO representation of the item
     * @return 0 if item to remove was krikon, otherwise 1
     */
    @Override
    public int removeItemFromStore(ItemBO itemBO) {
        if(itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    /**
     * Simulates changing the amounts of items available in the sotre
     * @param itemBO a BO representation of the item
     * @return 0 for krikon, otherwise 1
     */
    @Override
    public int changeAmountFromStore(ItemBO itemBO) {
        if(itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    /**
     * Simulates adding an item to a users cart
     * @param itemBO BO representation of the item
     * @param userBO BO representation of the user
     * @return 0 if user is ragnar and item i krikon, otherwise 1
     */

    @Override
    public int addItemToCart(ItemBO itemBO, UserBO userBO) {
        if(userBO.getUsername().equals("ragnar") && itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    /**
     * Simulates changing the amount of items in a users cart
     * @param userBO BO representation of user
     * @param itemBO BO representation of item
     * @return 0 if item is krikon, 1 otherwise
     */
    @Override
    public int changeItemAmountFromCart(UserBO userBO, ItemBO itemBO) {
        if(itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }

    /**
     * Simulates removing an item from the cart
     * @param userBO BO representation of user
     * @param itemBO BO representation of item
     * @return 0 if user is ragnar and item is krikon, 1 otherwise
     */
    @Override
    public int removeItemFromCart(UserBO userBO, ItemBO itemBO) {
        if(userBO.getUsername().equals("ragnar") && itemBO.getName().equals("krikon"))
        {
            return 0;
        }
        return 1;
    }
}
