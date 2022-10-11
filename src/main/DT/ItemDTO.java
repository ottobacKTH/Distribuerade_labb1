package main.DT;

import java.util.Objects;

/**
 * A DT representation of the item
 */
public class ItemDTO {
    private int id;
    private String name;
    private int price;

    private int amount;

    /**
     * A full constructor for every field in the class
     * @param id the id of the item
     * @param name the name of the item
     * @param price the items price
     * @param amount the amount of articles corresponding to the item
     */
    public ItemDTO(int id, String name, int price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDTO itemDTO = (ItemDTO) o;

        if (id != itemDTO.id) return false;
        if (price != itemDTO.price) return false;
        if (amount != itemDTO.amount) return false;
        return Objects.equals(name, itemDTO.name);
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
     * Get the items id
     * @return the id of the item
     */
    public int getId() {
        return id;
    }

    /**
     * Assign an items id
     * @param id the id to be given to the item
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the name of the item
     * @return the items name
     */
    public String getName() {
        return name;
    }

    /**
     * Assign a name for the item
     * @param name the name to be assigned to the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the price of an item
     * @return the items price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Assign a price to the item
     * @param price the price to be assigned to the item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *Get the amount of items
     * @return the amount of articles corresponding to the item
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Assign the amount of articles corresponding to the item
     * @param amount the amount to be assigned
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
