/**
 * Authors; Otto & Habib
 */
package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface that defines all methods needed to manage the database.
 */
public interface ItemDatabaseService {
    /**
     * Get all items in store
     * @return List of BO representations of the items
     */
    public List<ItemBO> getStoreItems();

    /**
     * Regurns all items in the cart tied to a specified user
     * @param userBO a BO representation of the user
     * @return List of BO representations of the items
     */
    public List<ItemBO> getCartItems(UserBO userBO);

    /**
     * Add a new item to the store
     * @param itemBO BO representation of the item
     * @return how many rows were affected
     */
    public int addNewItemToStore(ItemBO itemBO) ;

    /**
     * Defines and executes a transactions that purchases all items in a users' shopping cart
     * Throws Exception at logical errors.
     * @param userBO A BO representation of the user
     * @return wether the transaction was successful or not
     */
    public boolean makePurchase(UserBO userBO);

    /**
     * Remove a specified item from the store
     * @param itemBO BO representation of the item
     * @return how many rows were affected
     */
    public int removeItemFromStore(ItemBO itemBO);

    /**
     * Change the amount of a specified item
     * @param itemBO a BO representation of the item
     * @return how many rows were affected
     */
    public int changeAmountFromStore(ItemBO itemBO);

    /**
     * Adds a specified item to a users cart
     * @param itemBO BO representation of the item
     * @param userBO BO representation of the user
     * @return how many rows were affected
     */
    public int addItemToCart(ItemBO itemBO, UserBO userBO);

    /**
     * Change the amount of a specified item
     * @param userBO BO representation of user
     * @param itemBO BO representation of item
     * @return how many items were affected
     */
    public int changeItemAmountFromCart(UserBO userBO, ItemBO itemBO);

    /**
     * Remove a specified item from a users cart
     * @param userBO BO representation of user
     * @param itemBO BO representation of item
     * @return how many rows were affected
     */
    public int removeItemFromCart(UserBO userBO, ItemBO itemBO) ;
}
