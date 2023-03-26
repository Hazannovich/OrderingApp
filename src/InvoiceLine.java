/**
 * The type Invoice line.
 */

public class InvoiceLine {
    private final Item item;
    private double lineTotal;

    /**
     * Instantiates a new Invoice line.
     *
     * @param item         the item in the line
     * @param itemQuantity the item quantity in the line
     */
    public InvoiceLine(Item item, int itemQuantity) {
        this.item = item;
        this.lineTotal = item.getItemPrice() * itemQuantity;
    }

    /**
     * Gets line total.
     *
     * @return the line total
     */
    public double getLineTotal() {
        return lineTotal;
    }

    /**
     * Gets item quantity.
     *
     * @return the item quantity
     */
    public int getItemQuantity() {
        return (int)(lineTotal/item.getItemPrice());
    }
    public Item getItem() {
        return item;
    }
    public void addItemQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid Quantity");
            return;
        }
        lineTotal += quantity*item.getItemPrice();
    }
    public String toString() {
        return item.toString() + " Quantity: " + getItemQuantity() + " Total: " + lineTotal +"$";
    }
}
