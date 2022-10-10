package main.DB;

import main.BO.ItemBO;
import main.BO.UserBO;

import java.util.List;

public class MockItemService implements ItemDatabaseService{
    @Override
    public List<ItemBO> getStoreItems() {
        return null;
    }

    @Override
    public List<ItemBO> getCartItems(UserBO userBO) {
        return null;
    }

    @Override
    public void addNewItemToStore(ItemBO itemBO) {

    }

    @Override
    public boolean makePurchase(UserBO userBO) {
        return false;
    }

    @Override
    public void removeItemFromStore(ItemBO itemBO) {

    }

    @Override
    public void changeAmountFromStore(ItemBO itemBO) {

    }

    @Override
    public void addItemToCart(ItemBO itemBO, UserBO userBO) {

    }

    @Override
    public void changeItemAmountFromCart(UserBO userBO, ItemBO itemBO) {

    }

    @Override
    public void removeItemFromCart(UserBO userBO, ItemBO itemBO) {

    }
}
