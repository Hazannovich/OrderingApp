// this class represents an item in the store from Mamman 11
// I will extend on it to RestaurantItem Object
public class Item {
    private final String itemName;
    private final double itemPrice;

    public Item(String name, double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("invalid price.");
        }
        if (name == null || name.trim().isEmpty() || containsNonAlphaNum(name)) {
            throw new IllegalArgumentException("invalid name.");
        }
        this.itemName = name.toLowerCase().trim();
        this.itemPrice = price;
    }

    boolean containsNonAlphaNum(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isSpaceChar(c) && !Character.isAlphabetic(c)) {
                return true;
            }
        }
        return false;
    }

    public String getItemName() {
        return this.itemName;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public String toString() {
        return "Item: " + this.itemName + " Price: " + this.itemPrice + "$";
    }
}
