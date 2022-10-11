package main.BO;

import junit.framework.TestCase;
import main.DB.ItemDatabaseService;
import main.DT.ItemDTO;
import main.DT.UserDTO;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class ItemManagementTest extends TestCase {

    private ItemManagement itemManagement = new ItemManagement();
    public void testGetStorage() {
        List<ItemDTO> list = new ArrayList<ItemDTO>();
        list.add(new ItemDTO(0,"apple",10,42));
        list.add(new ItemDTO(1,"pear",20,60));
        list.add(new ItemDTO(2,"orange",4,3));
        List<ItemDTO> gottenList = itemManagement.getStorage();
        for(int i = 0; i < 3; i++)
        {
            Assert.assertEquals(list.get(i),gottenList.get(i));
        }
    }

    public void testGetCart() {
        UserDTO userV = new UserDTO("vincent","password123","customer");
        UserDTO userG = new UserDTO("greg","password123","staff");
        UserDTO userR = new UserDTO("ragnar","password123","admin");
        List<ItemDTO> listV = new ArrayList<>();
        listV.add(new ItemDTO(0,"apple",10,2));
        listV.add(new ItemDTO(1,"pear",20,10));
        List<ItemDTO> listG = new ArrayList<>();
        listG.add(new ItemDTO(0,"apple",10,42));
        listG.add(new ItemDTO(1,"pear",20,60));
        listG.add(new ItemDTO(2,"orange",4,3));
        List<ItemDTO> listR = new ArrayList<>();

        List<ItemDTO> gottenList = itemManagement.getCart(userV);
        for(int i = 0; i < listV.size(); i++)
        {
            Assert.assertEquals(listV.get(i),gottenList.get(i));
        }
        gottenList = itemManagement.getCart(userG);
        for(int i = 0; i < listG.size(); i++)
        {
            Assert.assertEquals(listG.get(i),gottenList.get(i));
        }
        gottenList = itemManagement.getCart(userR);
        Assert.assertEquals(listR,gottenList);

    }


    public void testAddItemToCart() {
        UserDTO user = new UserDTO("vincent","password","customer");
        ItemDTO item = new ItemDTO(0,"apple",5,10);
        itemManagement.addItemToCart(item,user);

        user = new UserDTO("ragnar","password123","admin");
        item = new ItemDTO(1,"krikon",10,5);
        try
        {
            itemManagement.addItemToCart(item,user);
            fail("exception wasn't thrown");
        }
        catch (Exception e)
        {
            Assert.assertNotNull(e);
        }
    }

    public void testPurchaseCart() {
        UserDTO userV = new UserDTO("vincent","password123","customer");
        UserDTO userG = new UserDTO("greg","password123","staff");
        UserDTO userR = new UserDTO("ragnar","password123","admin");
        Assert.assertEquals(itemManagement.purchaseCart(userV),true);
        Assert.assertEquals(itemManagement.purchaseCart(userG),false);
        try
        {
            itemManagement.purchaseCart(userR);
            fail("Exception wasn't thrown");
        }
        catch (Exception e)
        {
            Assert.assertNotNull(e);
        }
    }
}