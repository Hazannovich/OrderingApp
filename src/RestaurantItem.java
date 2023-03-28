public class RestaurantItem extends Item {
    private final String itemType;

    public RestaurantItem(String name, String type, double price) {
        super(name, price);
        if (type == null || type.trim().isEmpty() || containsNonAlphaNum(type)) {
            type = "other";
        }
        this.itemType = type;
    }

    public String getItemType() {
        return this.itemType;
    }

    public boolean equals(Object o) {
        if (!(o instanceof RestaurantItem item)) {
            return false;
        }
        return item.getItemName().equals(this.getItemName());
    }
}
