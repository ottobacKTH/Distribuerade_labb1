package main.BO;

import main.DB.ItemDatabaseService;
import main.DB.ItemService;
import main.DT.ItemDTO;
import main.DT.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemManagement {

    private ItemDatabaseService itemService;

    public ItemManagement()
    {
        itemService = new ItemService();
    }
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
        itemService.addItemToCart(item,user);
    }
    public boolean purchaseCart(UserDTO userDTO)
    {
        UserBO user = DTOtoBO(userDTO);
        return itemService.makePurchase(user);
    }
    private ItemDTO BOtoDTO(ItemBO BO)
    {
        return new ItemDTO(BO.getId(),BO.getName(),BO.getPrice(), BO.getAmount());
    }
    private ItemBO DTOtoBO(ItemDTO DTO)
    {
        return new ItemBO(DTO.getId(),DTO.getName(),DTO.getPrice(), DTO.getAmount());
    }

    private UserDTO BOtoDTO(UserBO BO)
    {
        return new UserDTO(BO.getUsername(),BO.getPassword(),BO.getRoleStr());
    }
    private UserBO DTOtoBO(UserDTO DTO)
    {
        return new UserBO(DTO.getUsername(),DTO.getPassword(),DTO.getRole());
    }
}
