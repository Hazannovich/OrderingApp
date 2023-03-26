public class OrderLine extends InvoiceLine {
    public OrderLine(RestaurantItem item, int itemQuantity) {
        super(item, itemQuantity);
    }
    @Override
    public void addItemQuantity(int quantity) {
        // I don't like this solution but it works.
        // if you can offer a better one please let me know, so I can learn from it.
        super.addItemQuantity(-this.getItemQuantity());// remove the current quantity
        super.addItemQuantity(quantity);// add the new quantity
    }
    public boolean equals(Object o) {
        if (!(o instanceof OrderLine other)) {
            return false;
        }
        return this.getItem().equals(other.getItem());
    }
}
