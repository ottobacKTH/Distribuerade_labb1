package main.BO;

import main.DB.ItemService;
import main.DT.ItemDTO;

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

    private ItemDTO BOtoDTO(ItemBO BO)
    {
        return new ItemDTO(BO.getId(),BO.getName(),BO.getPrice(), BO.getAmount());
    }
    private ItemBO DTOtoBO(ItemDTO DTO)
    {
        return new ItemBO(DTO.getId(),DTO.getName(),DTO.getPrice(), DTO.getAmount());
    }
}
