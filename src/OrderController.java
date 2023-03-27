import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.util.HashMap;

import static java.lang.Double.parseDouble;

public class OrderController {
    private double cart = 0.0;
    private final Menu menu = new Menu("menu.txt");
    HashMap<Integer, RestaurantItem> itemsHashMap = new HashMap<Integer, RestaurantItem>();
    @FXML
    private GridPane menuGrid;
    @FXML
    private Label starterLabel;
    @FXML
    private Rectangle starterRect;
    @FXML
    private Label totalCost;
    Order order = new Order();
    public void initialize() {
        int rowIndex = 1;
        for (Item item : menu.getItemsByType("starter")) {
            addItemToGrid(rowIndex++, item);
        }
        setHeader("Mains:", rowIndex++);
        for (Item item : menu.getItemsByType("main")) {
            addItemToGrid(rowIndex++, item);
        }
        setHeader("Lasts:", rowIndex++);
        for (Item item : menu.getItemsByType("last")) {
            addItemToGrid(rowIndex++, item);
        }
        setHeader("Drinks:", rowIndex++);
        for (Item item : menu.getItemsByType("drink")) {
            addItemToGrid(rowIndex++, item);
        }
    }

    private void addItemToGrid(int rowIndex, Item item) {
        MenuRow menuRow = new MenuRow((RestaurantItem) item);
        menuGrid.addRow(rowIndex);
        menuGrid.add(menuRow.getName(), 0, rowIndex);
        menuGrid.add(menuRow.getPrice(), 1, rowIndex);
        menuGrid.add(menuRow.getQuantityBox(), 2, rowIndex);
        menuGrid.add(menuRow.getAdd(), 3, rowIndex);
        itemsHashMap.put(rowIndex, (RestaurantItem) item);
        menuRow.getAdd().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer quantity;
                if (menuRow.getAdd().isSelected()) {
                    quantity = (Integer) menuRow.getQuantityBox().getValue();
                    OrderLine orderLine = new OrderLine(menuRow.getItem(), quantity);
                    order.addOrderLine(orderLine);

                } else {
                    OrderLine line = order.findOrderLine(menuRow.getItem());
                    order.removeOrderLine(line);
                }
                totalCost.setText(order.getOrderTotal() + "$");
            }
        });
        menuRow.getQuantityBox().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (menuRow.getAdd().isSelected()) {
                    order.updateLineQuantity(menuRow.getItem(), (Integer) menuRow.getQuantityBox().getValue());
                    totalCost.setText(order.getOrderTotal() + "$");
                }
            }
        });
    }

    @FXML
    void OrderBtnHandler(ActionEvent event) {
        // create a text input dialog for ID
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Enter ID");
        idDialog.setHeaderText(null);
        idDialog.setContentText("Please enter your ID:");

        // create a text input dialog for Name
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Enter Name");
        nameDialog.setHeaderText(null);
        nameDialog.setContentText("Please enter your Name:");

        // show the input dialog boxes
        idDialog.showAndWait();
        String userId = idDialog.getResult();

        nameDialog.showAndWait();
        String userName = nameDialog.getResult();

        // use the entered values
        System.out.println("User ID: " + userId);
        System.out.println("User Name: " + userName);

    }

    private void setHeader(String headerName, int rowIndex) {
        Label headerLabel = new Label(headerName);
        Rectangle headerRect = new Rectangle();
        headerLabel.setFont(starterLabel.getFont());
        headerRect.setHeight(starterRect.getHeight());
        headerRect.setWidth(starterRect.getWidth());
        headerRect.setFill(starterRect.getFill());
        headerRect.setEffect(starterRect.getEffect());
        menuGrid.add(headerRect, 0, rowIndex);
        menuGrid.add(headerLabel, 0, rowIndex);
    }

}

