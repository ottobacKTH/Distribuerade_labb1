package main.BO;

import main.DB.ItemService;
import main.DT.ItemDTO;
import main.DT.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemManagement {
    public List<ItemDTO> getStorage()
    {
        List<ItemBO> BOlist = ItemService.getStoreItems();
        List<ItemDTO> DTOlist = new ArrayList<>();
        for(int i = 0; i < BOlist.size(); i++)
        {
            DTOlist.add(BOtoDTO(BOlist.get(i)));
        }
        return DTOlist;
    }

    public List<ItemDTO> getCart(UserDTO userDTO)
    {
        List<ItemBO> BOlist = ItemService.getCartItems(DTOtoBO(userDTO));
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
        ItemService.addItemToCart(item,user);
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
        return new UserDTO(BO.getUsername(),BO.getPassword(),BO.getRole());
    }
    private UserBO DTOtoBO(UserDTO DTO)
    {
        return new UserBO(DTO.getUsername(),DTO.getPassword(),DTO.getRole());
    }
}
