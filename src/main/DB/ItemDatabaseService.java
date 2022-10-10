package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDatabaseService {
    public List<ItemBO> getStoreItems();
    public List<ItemBO> getCartItems(UserBO userBO);
    public int addNewItemToStore(ItemBO itemBO) ;
    public boolean makePurchase(UserBO userBO);
    public int removeItemFromStore(ItemBO itemBO);
    public int changeAmountFromStore(ItemBO itemBO);
    public int addItemToCart(ItemBO itemBO, UserBO userBO);
    public int changeItemAmountFromCart(UserBO userBO, ItemBO itemBO);
    public int removeItemFromCart(UserBO userBO, ItemBO itemBO) ;
}
