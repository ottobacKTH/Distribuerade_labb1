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
    public void addNewItemToStore(ItemBO itemBO) ;
    public boolean makePurchase(UserBO userBO);
    public void removeItemFromStore(ItemBO itemBO);
    public void changeAmountFromStore(ItemBO itemBO);
    public void addItemToCart(ItemBO itemBO, UserBO userBO);
    public void changeItemAmountFromCart(UserBO userBO, ItemBO itemBO);
    public void removeItemFromCart(UserBO userBO, ItemBO itemBO) ;
}
