import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderLine> orderLines;

    public Order() {
        this.orderLines = new ArrayList<>();
    }
    public void addOrderLine(OrderLine orderLine) {
        for (OrderLine line : this.orderLines) {
            if (line.equals(orderLine)) {
                line.addItemQuantity(orderLine.getItemQuantity());
                return;
            }
        }
        this.orderLines.add(orderLine);
    }
    public OrderLine findOrderLine(RestaurantItem item) {
        for (OrderLine line : this.orderLines) {
            if (line.getItem().equals(item)) {
                return line;
            }
        }
        return null;
    }
    public void removeOrderLine(OrderLine orderLine) {
        this.orderLines.remove(orderLine);
    }
    public double getOrderTotal() {
        double total = 0.0;
        for (OrderLine line : this.orderLines) {
            total += line.getLineTotal();
        }
        return total;
    }
    public void updateLineQuantity(RestaurantItem item, Integer quantity) {
        for (OrderLine line : this.orderLines) {
            if (line.getItem().equals(item)) {
                line.addItemQuantity(quantity);
                return;
            }
        }
    }
}
