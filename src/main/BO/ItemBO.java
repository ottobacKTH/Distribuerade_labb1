package main.BO;

import java.util.Objects;

/**
 * Representationsklass för item i BO lagret.
 */
public class ItemBO {
    private int id;
    private String name;
    private int price;
    private int amount;

    /**
     * Full constructor for each parameter
     * @param id
     * @param name
     * @param price
     * @param amount
     */
    public ItemBO(int id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    /**
     * get amount of items
     * @return amount of items
     */
    public int getAmount() {
        return amount;
    }

    /**
     * assign how many items
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemBO itemBO = (ItemBO) o;

        if (id != itemBO.id) return false;
        if (price != itemBO.price) return false;
        if (amount != itemBO.amount) return false;
        return Objects.equals(name, itemBO.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + amount;
        return result;
    }

    /**
     * get items' id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * set items' id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get name of the item
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * assign name to the item
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get price of item
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * set price of item
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
