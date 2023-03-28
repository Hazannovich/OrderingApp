import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class OrderController {
    private final Menu menu = new Menu("menu.txt");
    Order order;
    @FXML
    private GridPane menuGrid;
    @FXML
    private Label starterLabel;
    @FXML
    private Rectangle starterRect;
    @FXML
    private Label totalCost;
    private boolean isInitialized = false;
    private int rowIndex;

    public void initialize() {
        order = new Order();
        totalCost.setText(order.getOrderTotal() + "$");
        rowIndex = 0;
        initType("Starters:");
        initType("Mains:");
        initType("Lasts:");
        initType("Drinks:");
        isInitialized = true;
    }

    private void initType(String headerName) {
        setHeader(headerName, rowIndex++);
        String main = headerName.substring(0, headerName.length() - 2).toLowerCase();
        for (Item item : menu.getItemsByType(main)) {
            addItemToGrid(rowIndex++, item);
        }
    }

    private void addItemToGrid(int rowIndex, Item item) {
        MenuRow menuRow = new MenuRow((RestaurantItem) item);
        menuGrid.add(menuRow.getName(), 0, rowIndex);
        menuGrid.add(menuRow.getPrice(), 1, rowIndex);
        menuGrid.add(menuRow.getQuantityBox(), 2, rowIndex);
        menuGrid.add(menuRow.getAdd(), 3, rowIndex);
        menuRow.getAdd().setOnAction(event -> {
            AddCheckChange(menuRow);
        });
        menuRow.getQuantityBox().setOnAction(event -> {
            quantityChangeHandler(menuRow);
        });
    }

    private void AddCheckChange(MenuRow menuRow) {
        InvoiceLine orderLine;
        if (menuRow.getAdd().isSelected()) {
            Integer quantity = (Integer) menuRow.getQuantityBox().getValue();
            orderLine = new InvoiceLine(menuRow.getItem(), quantity);
            order.addOrderLine(orderLine);
        } else {
            orderLine = order.findOrderLine(menuRow.getItem());
            order.removeOrderLine(orderLine);
        }
        totalCost.setText(order.getOrderTotal() + "$");
    }

    private void quantityChangeHandler(MenuRow menuRow) {
        if (menuRow.getAdd().isSelected()) {
            Integer quantity = (Integer) menuRow.getQuantityBox().getValue();
            RestaurantItem item = menuRow.getItem();
            order.updateLineQuantity(item, quantity);
            totalCost.setText(order.getOrderTotal() + "$");
        }
    }

    @FXML
    void orderBtnHandler(ActionEvent event) {

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirm Order");
        confirmDialog.setHeaderText("Continue to checkout?");
        confirmDialog.setContentText(order.toString());

        // customize the confirmation dialog buttons
        ButtonType confirmButton = new ButtonType("Confirm order", ButtonData.OK_DONE);
        ButtonType editButton = new ButtonType("Edit order", ButtonData.CANCEL_CLOSE);
        confirmDialog.getButtonTypes().clear();
        confirmDialog.getButtonTypes().addAll(confirmButton, editButton);

        // show the confirmation dialog and wait for a response
        confirmDialog.showAndWait().ifPresent(response -> {
            if (response == confirmButton) {
                // create a text input dialog for Name
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setTitle("Enter Name");
                nameDialog.setHeaderText(null);
                nameDialog.setContentText("Please enter your Name:");
                // create a text input dialog for ID
                TextInputDialog idDialog = new TextInputDialog();
                idDialog.setTitle("Enter ID");
                idDialog.setHeaderText(null);
                idDialog.setContentText("Please enter your ID:");

                // show the input dialog boxes
                nameDialog.showAndWait();
                String userName = nameDialog.getResult();
                idDialog.showAndWait();
                // create a new costumer and generate a receipt in assets/receipts
                String userId = idDialog.getResult();
                Costumer costumer = new Costumer(userName, userId);
                costumer.generateReceipt(order);
                initialize();
            }
        });
    }


    private void setHeader(String headerName, int rowIndex) {
        if (isInitialized || headerName.equals("Starters:")) {
            return;
        }
        Label headerLabel = new Label(headerName);
        Rectangle headerRect = new Rectangle();
        headerLabel.setFont(starterLabel.getFont());
        headerLabel.setPadding(starterLabel.getPadding());
        headerRect.setHeight(starterRect.getHeight());
        headerRect.setWidth(starterRect.getWidth());
        headerRect.setFill(starterRect.getFill());
        headerRect.setEffect(starterRect.getEffect());
        headerRect.setOpacity(starterRect.getOpacity());
        menuGrid.add(headerRect, 0, rowIndex);
        menuGrid.add(headerLabel, 0, rowIndex);
    }

}

