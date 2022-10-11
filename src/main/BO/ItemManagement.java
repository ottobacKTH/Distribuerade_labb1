/**
 * Authors; Otto & Habib
 */
package main.BO;

import main.DB.ItemDatabaseService;
import main.DB.ItemService;
import main.DB.MockItemService;
import main.DT.ItemDTO;
import main.DT.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for handling BO objects.
 * This class can never return a BO object to another class.
 * Will use BO objects to communicate with DB layer
 */
public class ItemManagement {


    private ItemDatabaseService itemService;

    /**
     * the only constructor for the class, used to initiate the objects handle into the DB layer.
     */
    public ItemManagement()
    {
        itemService = new ItemService();
    }

    /**
     * get all the items in storage
     * @return DTO representations of the items in a list
     */
    public List<ItemDTO> getStorage()
    {
        List<ItemBO> BOlist = itemService.getStoreItems();
        List<ItemDTO> DTOlist = new ArrayList<>();
        for(int i = 0; i < BOlist.size(); i++)
        {
            DTOlist.add(BOtoDTO(BOlist.get(i)));
        }
        return DTOlist;
    }

    /**
     * Get the users cart in the form of a list
     * @param userDTO A DT representation of the user holding the items
     * @return a list of DT representations of the items
     */
    public List<ItemDTO> getCart(UserDTO userDTO)
    {
        List<ItemBO> BOlist = itemService.getCartItems(DTOtoBO(userDTO));
        List<ItemDTO> DTOlist = new ArrayList<>();
        for(int i = 0; i < BOlist.size(); i++)
        {
            DTOlist.add(BOtoDTO(BOlist.get(i)));
        }
        return DTOlist;
    }

    /**
     * Adds an item to a specific user. Will throw exceptions if the item was invalid or it couldn't be added
     * @param itemDTO DT representation of the item to be added
     * @param userDTO DT representation of the user that wants the item
     */
    public void addItemToCart(ItemDTO itemDTO, UserDTO userDTO) {
        ItemBO item = DTOtoBO(itemDTO);
        UserBO user = DTOtoBO(userDTO);
        if(item==null)
        {
            throw new IllegalStateException("The item you tried to add was null");
        }
        if(item.getAmount() <= 0)
        {
            throw new IllegalStateException("can't add 0 or less items");
        }
        if(itemService.addItemToCart(item,user) == 0)
        {
            throw new IllegalStateException("item couldn't be added");
        }

    }

    /**
     * Purchases the users cart
     * @param userDTO DT representation of the user that wants to make the purchase
     * @return
     */
    public boolean purchaseCart(UserDTO userDTO)
    {
        UserBO user = DTOtoBO(userDTO);
        return itemService.makePurchase(user);
    }

    /**
     * Converts a BO representation of the item to a DT representation
     * @param BO BO representation of item
     * @return DT representation of item
     */
    private ItemDTO BOtoDTO(ItemBO BO)
    {
        return new ItemDTO(BO.getId(),BO.getName(),BO.getPrice(), BO.getAmount());
    }

    /**
     * Converts a DT representation of the item to a BO representation
     * @param DTO DT representation of item
     * @return BO representation of item
     */
    private ItemBO DTOtoBO(ItemDTO DTO)
    {
        return new ItemBO(DTO.getId(),DTO.getName(),DTO.getPrice(), DTO.getAmount());
    }

    /**
     * Converts a BO representation of the user to a DT representation, requires role to be valid
     * @param BO BO representation of user
     * @return DT representation of user
     */
    private UserDTO BOtoDTO(UserBO BO)
    {
        return new UserDTO(BO.getUsername(),BO.getPassword(),BO.getRoleStr());
    }
    /**
     * Converts a DT representation of the user to a BO representation
     * @param DTO DT representation of user
     * @return BO representation of user
     */
    private UserBO DTOtoBO(UserDTO DTO)
    {
        return new UserBO(DTO.getUsername(),DTO.getPassword(),DTO.getRole());
    }
}
