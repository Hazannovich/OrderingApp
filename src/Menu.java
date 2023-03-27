import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu extends Inventory {

    public Menu(String filePath) {
        super();
        Scanner input = null;
        try {// different OS have different file separators But I am not sure if there is a better workaround.
            input = new Scanner(new File("assets" + System.getProperty("file.separator") + filePath));
        } catch (FileNotFoundException e) {
            System.out.println("The menu file does not exist.");
            System.exit(0);
        } catch (SecurityException e) {
            try {
                input = new Scanner(new File("assets" + "\\" + filePath));
            } catch (FileNotFoundException e1) {
                System.out.println("The menu file does not exist.");
                System.exit(0);
            }
        }
        while (input.hasNextLine()) {
            String[] item = new String[3];
            try {
                item[0] = input.nextLine(); // Description of item
                item[1] = input.nextLine().toLowerCase(); // Type of item
                item[2] = input.nextLine(); // Price of item
                String name = item[0];
                String type = item[1];
                double price = Double.parseDouble(item[2]);
                addItem(new RestaurantItem(name, type, price));
            } catch (Exception e) {
                System.out.println("Some items have invalid information and have been removed.");
            }
        }
        input.close();
    }
    public List<RestaurantItem> getItemsByType(String type) {
        List<RestaurantItem> items = new ArrayList<>();
        for (Item item : this.inventory) {
            if (item instanceof RestaurantItem restaurantItem) {
                if (restaurantItem.getItemType().equals(type)) {
                    items.add(restaurantItem);
                }
            }
        }
        return items;
    }
    public int getNumberOfItems() {
        return this.inventory.size();
    }
    public List<RestaurantItem> getItems() {
        List<RestaurantItem> items = new ArrayList<>();
        for (Item item : this.inventory) {
            if (item instanceof RestaurantItem restaurantItem) {
                items.add(restaurantItem);
            }
        }
        return items;
    }
}
