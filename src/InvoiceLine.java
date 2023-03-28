/**
 * The type Invoice line.
 */

public class InvoiceLine {
    private final Item item;
    private int itemQuantity;

    /**
     * Instantiates a new Invoice line.
     *
     * @param item         the item in the line
     * @param itemQuantity the item quantity in the line
     */
    public InvoiceLine(Item item, int itemQuantity) {
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    /**
     * Gets line total.
     *
     * @return the line total
     */
    public double getLineTotal() {
        return item.getItemPrice() * itemQuantity;
    }

    /**
     * Gets item quantity.
     *
     * @return the item quantity
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    public Item getItem() {
        return item;
    }

    public void addItemQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid Quantity");
            return;
        }
        this.itemQuantity = quantity;
    }

    public boolean equals(Object o) {
        if (!(o instanceof InvoiceLine other)) {
            return false;
        }
        return this.getItem().equals(other.getItem());
    }

    public String toString() {
        return item.toString() + " Quantity: " + getItemQuantity() + " Total: " + getLineTotal() + "$";
    }
}
