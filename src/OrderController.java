import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class OrderController {

    private final Menu menu = new Menu("menu.txt");
    @FXML
    private GridPane menuGrid;
    @FXML
    private Label starterLabel;
    @FXML
    private Rectangle starterRect;
    @FXML
    private Label totalLabel;

    public void initialize() {
        int rowIndex = 1;
        for (Item item : menu.getItemsByType("starter")) {
            addItemToGrid(rowIndex++, item);
        }
        Label mainLabel = new Label("Mains:");
        Rectangle mainRect = new Rectangle();
        mainLabel.setFont(starterLabel.getFont());
        mainRect.setHeight(starterRect.getHeight());
        mainRect.setWidth(starterRect.getWidth());
        mainRect.setFill(starterRect.getFill());
        mainRect.setEffect(starterRect.getEffect());
        menuGrid.add(mainRect, 0, rowIndex);
        menuGrid.add(mainLabel, 0, rowIndex++);
        for (Item item : menu.getItemsByType("main")) {
            addItemToGrid(rowIndex++, item);
        }
        Label lastLabel = new Label("Lasts:");
        Rectangle lastRect = new Rectangle();
        lastLabel.setFont(starterLabel.getFont());
        lastRect.setHeight(starterRect.getHeight());
        lastRect.setWidth(starterRect.getWidth());
        lastRect.setFill(starterRect.getFill());
        lastRect.setEffect(starterRect.getEffect());
        menuGrid.add(lastRect, 0, rowIndex);
        menuGrid.add(lastLabel, 0, rowIndex++);
        for (Item item : menu.getItemsByType("last")) {
            addItemToGrid(rowIndex++, item);
        }
        lastLabel = new Label("Drinks:");
        lastRect = new Rectangle();
        lastLabel.setFont(starterLabel.getFont());
        lastRect.setHeight(starterRect.getHeight());
        lastRect.setWidth(starterRect.getWidth());
        lastRect.setFill(starterRect.getFill());
        lastRect.setEffect(starterRect.getEffect());
        menuGrid.add(lastRect, 0, rowIndex);
        menuGrid.add(lastLabel, 0, rowIndex++);
        for (Item item : menu.getItemsByType("drink")) {
            addItemToGrid(rowIndex++, item);
        }
    }

    private void addItemToGrid(int rowIndex, Item item) {
        Label name = new Label(item.getItemName());
        Label price = new Label(item.getItemPrice() + "$");
        ComboBox quantity = new ComboBox<>();
        CheckBox add = new CheckBox();
        name.setStyle("-fx-font-size: 16px;");
        price.setStyle("-fx-font-size: 16px;");
        quantity.setStyle("-fx-font-size: 11px;");
        add.setStyle("-fx-font-size: 16px;");
        quantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        menuGrid.addRow(rowIndex);
        menuGrid.add(name, 0, rowIndex);
        menuGrid.add(price, 1, rowIndex);
        menuGrid.add(quantity, 2, rowIndex);
        menuGrid.add(add, 3, rowIndex);
    }

    @FXML
    void OrderBtnHandler(ActionEvent event) {

    }

}
