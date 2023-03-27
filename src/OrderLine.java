public class OrderLine extends InvoiceLine {
    public OrderLine(RestaurantItem item, int itemQuantity) {
        super(item, itemQuantity);
    }

    public boolean equals(Object o) {
        if (!(o instanceof OrderLine other)) {
            return false;
        }
        return this.getItem().equals(other.getItem());
    }
}
