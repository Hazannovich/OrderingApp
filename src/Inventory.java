import java.util.ArrayList;
import java.util.List;

/**
 * The type Inventory.
 * This class represents Inventory from Maman 11
 * I will extend it to Menu Object(which is a type of Inventory I think)
 * technically there is also inventory of grocery items for the chef,
 * but it is out of scope for this assignment.
 */
public class Inventory {
    private final List<Item> inventory;

    /**
     * Instantiates a new Inventory.
     */
    public Inventory() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(Item item) {
        if (lookUpItem(item.getItemName()) != null) {
            System.out.println("Item already exists in inventory.");
            return;
        }
        this.inventory.add(item);
    }
    public String toString() {
        StringBuilder items = new StringBuilder();
        for (Item item : this.inventory) {
            items.append(item.toString()).append("\n");
        }
        return items.toString();
    }

    /**
     * Look up item item.
     *
     * @param itemName the item name
     * @return the item
     */
    public Item lookUpItem(String itemName) {
        for (Item item : this.inventory) {
            if (item.getItemName().equals(itemName.toLowerCase().trim())) {
                return item;
            }
        }
        return null;
    }

    /**
     * Remove item.
     *
     * @param itemName the item name
     */
    public void removeItem(String itemName) {
        Item item = lookUpItem(itemName);
        if (item == null) {
            System.out.println("Item not found in inventory.");
            return;
        }
        this.inventory.remove(item);
    }
}
