import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

        AtomicReference<Alert> confirmDialog = new AtomicReference<>(new Alert(Alert.AlertType.CONFIRMATION));
        confirmDialog.get().setTitle("Confirm Order");
        confirmDialog.get().setHeaderText("Continue to checkout?");
        confirmDialog.get().setContentText(order.toString());

        // customize the confirmation dialog buttons
        ButtonType confirmButton = new ButtonType("Confirm order", ButtonData.OK_DONE);
        ButtonType editButton = new ButtonType("Edit order", ButtonData.CANCEL_CLOSE);
        confirmDialog.get().getButtonTypes().clear();
        confirmDialog.get().getButtonTypes().addAll(confirmButton, editButton);

        // show the confirmation dialog and wait for a response
        confirmDialog.get().showAndWait().ifPresent(response -> {
            if (response == confirmButton) {
                // create a text input dialog for Name
                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Checkout");
                dialog.setHeaderText("Enter your Personal Details");
                ButtonType orderButtonType = new ButtonType("Order Now", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(orderButtonType, ButtonType.CANCEL);
                // create a new costumer and generate a receipt in assets/receipts
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField costumerName = new TextField();
                costumerName.setPromptText("e.g. John");
                TextField costumerID = new TextField();
                costumerID.setPromptText("e.g. 123456789");

                grid.add(new Label("Your Name:"), 0, 0);
                grid.add(costumerName, 1, 0);
                grid.add(new Label("Your ID:"), 0, 1);
                grid.add(costumerID, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
                Node orderButton = dialog.getDialogPane().lookupButton(orderButtonType);
                orderButton.setDisable(true);

                //TODO: ADD VALIDATION FOR ID AND NAME (ONLY NUMBERS AND LETTERS)
                costumerName.textProperty().addListener((observable, oldValue, newValue) -> {
                    orderButton.setDisable(newValue.trim().isEmpty());
                });
                dialog.getDialogPane().setContent(grid);


// Convert the result to a username-password-pair when the login button is clicked.
                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == orderButtonType) {
                        return new Pair<>(costumerName.getText(), costumerID.getText());
                    }
                    return null;
                });

                Optional<Pair<String, String>> result = dialog.showAndWait();

                result.ifPresent(usernamePassword -> {
                    Costumer costumer = new Costumer(usernamePassword.getKey(), usernamePassword.getValue());
                    costumer.generateReceipt(order);
                    Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
                    dialog2.setTitle("Confirmation");
                    dialog2.setHeaderText("Your order has been placed successfully!");
                    dialog2.setContentText("Returning to main menu...");
                    Optional<ButtonType> congrats = dialog2.showAndWait();
                });
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

