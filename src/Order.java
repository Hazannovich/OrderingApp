import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<InvoiceLine> orderLines;

    public Order() {
        this.orderLines = new ArrayList<>();
    }

    public void addOrderLine(InvoiceLine orderLine) {
        for (InvoiceLine line : this.orderLines) {
            if (line.equals(orderLine)) {
                line.addItemQuantity(orderLine.getItemQuantity());
                return;
            }
        }
        this.orderLines.add(orderLine);
    }

    public InvoiceLine findOrderLine(Item item) {
        for (InvoiceLine line : this.orderLines) {
            if (line.getItem().equals(item)) {
                return line;
            }
        }
        return null;
    }

    public void removeOrderLine(InvoiceLine orderLine) {
        this.orderLines.remove(orderLine);
    }

    public double getOrderTotal() {
        double total = 0.0;
        for (InvoiceLine line : this.orderLines) {
            total += line.getLineTotal();
        }
        return total;
    }

    public void updateLineQuantity(RestaurantItem item, Integer quantity) {
        for (InvoiceLine line : this.orderLines) {
            if (line.getItem().equals(item)) {
                line.addItemQuantity(quantity);
                return;
            }
        }
    }

    public String toString() {
        StringBuilder order = new StringBuilder();
        for (InvoiceLine line : this.orderLines) {
            order.append(line.toString()).append("\n");
        }
        order.append("Total: ").append(getOrderTotal()).append("$\n");

        return order.toString();
    }
}
