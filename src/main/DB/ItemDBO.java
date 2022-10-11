package main.DB;

import java.util.Objects;

/**
 * DB representation of the item
 */
public class ItemDBO {
    private int id;
    private String name;
    private int price;
    private int amount;

    /**
     * full constructor
     * @param id the item id, unique in the database and mainly generated in the database
     * @param name the name of the item
     * @param price the price of the item
     * @param amount the amount of items
     */
    public ItemDBO(int id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    /**
     * An empty constructor, initiates nothing
     */
    public ItemDBO() {
    }

    /**
     * gets the corresponding amount to an item
     * @return amount of items
     */
    public int getAmount() {
        return amount;
    }

    /**
     * assign amount of items
     * @param amount amount of items
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDBO itemDBO)) return false;

        if (id != itemDBO.id) return false;
        if (price != itemDBO.price) return false;
        return Objects.equals(name, itemDBO.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    /**
     * get items id
     * @return item id
     */
    public int getId() {
        return id;
    }

    /**
     * assign items id
     * @param id item id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get an items name
     * @return items name
     */
    public String getName() {
        return name;
    }

    /**
     * assign an items name
     * @param name items name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get an items price
     * @return price of item
     */
    public int getPrice() {
        return price;
    }

    /**
     * assign a price to the item
     * @param price price of item
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
