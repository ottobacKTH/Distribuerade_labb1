package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    public List<ItemBO> getStoreItems() {
        ArrayList<ItemDBO> list = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from item");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                list.add(new ItemDBO(id, name, price, amount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ItemBO> BOlist = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            BOlist.add(DBOtoBO(list.get(i)));
        }
        return BOlist;
    }

    public List<ItemBO> getCartItems(UserBO userBO)
    {
        ArrayList<ItemDBO> list = new ArrayList<>();
        UserDBO user = BOtoDBO(userBO);
        try {
            Connection connection = DBManager.getConnection();
            String sql = ("SELECT i.id, i.name, i.price, s.amount FROM item i, shopping_cart s WHERE s.username = ? AND i.id = s.item_id");
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1,user.getUsername());
            pStatement.execute();
            ResultSet rs = pStatement.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                list.add(new ItemDBO(id, name, price, amount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ItemBO> BOlist = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            BOlist.add(DBOtoBO(list.get(i)));
        }
        return BOlist;
    }
    public void addNewItemToStore(ItemBO itemBO) {
        ItemDBO item = BOtoDBO(itemBO);
        try {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO item (name, price, amount) VALUES (?,?,?)";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1,item.getName());
            pStatement.setInt(2,item.getPrice());
            pStatement.setInt(3,item.getAmount());
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean makePurchase(UserBO userBO) {
        UserDBO user = BOtoDBO(userBO);
        try
        {
            Connection connection = DBManager.getConnection();
            connection.setAutoCommit(false);

            List<ItemDBO> itemList = new ArrayList<>();
            String sql = "SELECT i.id, i.name, i.price, i.amount AS item_amount, s.amount AS cart_amount FROM item i, shopping_cart s WHERE s.username = ? AND i.id = s.item_id ORDER BY i.id";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1,user.getUsername());
            pStatement.execute();
            ResultSet rs = pStatement.getResultSet();
            while(rs.next())
            {
                ItemDBO fetchedItem = new ItemDBO(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("item_amount") - rs.getInt("cart_amount"));
                if(fetchedItem.getAmount() < 0)
                {
                    throw new IllegalStateException("too many wares of type: " + fetchedItem.getName() + " in your cart");
                }
                itemList.add(fetchedItem);
            }
            if(itemList.size() == 0)
            {
                throw new IllegalStateException("no items in cart");
            }

            sql = "UPDATE item i, shopping_cart s SET i.amount = (i.amount - s.amount) WHERE s.username = ? AND i.id = s.item_id";
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, user.getUsername());
            pStatement.execute();

            sql = "DELETE FROM shopping_cart WHERE username = ?";
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, user.getUsername());
            pStatement.execute();

            connection.setAutoCommit(true);
            return true;
        }
        catch (SQLException sqlException)
        {
            Connection connection = DBManager.getConnection();
            try
            {
                connection.rollback();
            }
            catch (SQLException rollbackException)
            {
                rollbackException.printStackTrace();
            }
            sqlException.printStackTrace();
            return false;
        }
    }
    public void removeItemFromStore(int id) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "DELETE FROM item WHERE id = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1,id);
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeAmountFromStore(int id, int amount) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "Update item SET amount = ? WHERE id = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1,amount);
            pStatement.setInt(2,id);
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void addItemToCart(ItemBO itemBO, UserBO userBo) {
        ItemDBO item = BOtoDBO(itemBO);
        UserDBO user = BOtoDBO(userBo);
        try
        {
            Connection connection = DBManager.getConnection();
            String sql = "INSERT INTO shopping_cart (username, item_id, amount) VALUES (?,?,?)";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, user.getUsername());
            pStatement.setInt(2, item.getId());
            pStatement.setInt(3, item.getAmount());
            pStatement.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void changeItemAmountFromCart(String username, int itemID, int amount) {
        try
        {
            Connection connection = DBManager.getConnection();
            String sql = "Update shopping_cart SET amount = ? WHERE username = ? AND item_id = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, amount);
            pStatement.setString(2, username);
            pStatement.setInt(3, itemID);
            pStatement.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void removeItemFromCart(String username, int itemID) {
        try
        {
            Connection connection = DBManager.getConnection();
            String sql = "DELETE FROM shopping_cart WHERE username = ? AND item_id = ?";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, username);
            pStatement.setInt(2, itemID);
            pStatement.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private ItemDBO BOtoDBO(ItemBO BO)
    {
        return new ItemDBO(BO.getId(),BO.getName(),BO.getPrice(),BO.getAmount());
    }
    private ItemBO DBOtoBO(ItemDBO DBO)
    {
        return new ItemBO(DBO.getId(),DBO.getName(),DBO.getPrice(),DBO.getAmount());
    }
    private UserDBO BOtoDBO(UserBO BO)
    {
        return new UserDBO(BO.getUsername(), BO.getPassword(), BO.getRole());
    }

}

