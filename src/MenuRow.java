import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MenuRow {
    protected Label name;
    protected Label price;
    protected ComboBox<Object> quantityBox;
    protected CheckBox add;
    protected RestaurantItem item;

    public MenuRow(RestaurantItem item) {
        this.item = item;
        name = new Label(item.getItemName());
        price = new Label(item.getItemPrice() + "$");
        quantityBox = new ComboBox<>();
        add = new CheckBox();
        name.setStyle("-fx-padding: 0 0 0 5;-fx-font-size: 16px;");
        price.setStyle("-fx-font-size: 16px;");
        quantityBox.setStyle("-fx-font-size: 11px;");
        add.setStyle("-fx-font-size: 16px;");
        quantityBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        quantityBox.getSelectionModel().selectFirst();
    }

    public Label getName() {
        return name;
    }

    public Label getPrice() {
        return price;
    }

    public ComboBox<Object> getQuantityBox() {
        return quantityBox;
    }

    public CheckBox getAdd() {
        return add;
    }

    public RestaurantItem getItem() {
        return item;
    }
}
