import java.util.ArrayList;
import java.util.List;

public class Costumer {
    private final String name;
    private final String id;

    public Costumer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void generateReceipt(Order order) {

    }

    public String getId() {
        return this.id;
    }


    public String toString() {
        return "Name: " + this.name + " ID: " + this.id;
    }
}
