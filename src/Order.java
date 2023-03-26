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
    public void removeOrderLine(OrderLine orderLine) {
        this.orderLines.remove(orderLine);
    }

}
