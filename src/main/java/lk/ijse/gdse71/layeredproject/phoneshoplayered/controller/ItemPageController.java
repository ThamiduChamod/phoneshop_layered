package lk.ijse.gdse71.layeredproject.phoneshoplayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.BOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.ItemBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.ItemBOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.DAOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.CategoryDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.ItemDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.PaymentDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.CategoryDAOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CategoryDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Category;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Payment;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemPageController implements Initializable {
    public AnchorPane itemAnchorPane;
    public Button newItemBtn;
    public Button updateItemBtn;
    public AnchorPane newItemAnchorPane;
    public Label lblItemId;
    public ComboBox cmbBrandname;
    public Label categoryIdLBL;
    public TextField txtName;
    public TextField txtQuantity;
    public TextField txtPrice;
    public Button btnReset;
    public TableColumn colItemId;
    public TableColumn colName;
    public TableColumn colQuantity;
    public TableColumn colPrice;
    public TableColumn colCategoryId;
    public TableView tblItem;
    public Button btnAddtoCart;
    public Label paymentIdLbl;
    public TableColumn colTotalPrice;
    public TableColumn colOnAction;
    public Button btnByItem;
    public Label supplierIdLbl;
    public Label dateLbl;
    public Label userLbl;
    public Button viewItemBtn;

    public static Date dates;
    public static String users;


    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOType.ITEM);

    private final ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();



    public void onClickTable(MouseEvent mouseEvent) {
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
    }

//    public void btnSaveItemOnAction(ActionEvent actionEvent) {
//
//        String itemId = lblItemId.getText();
//        String categoryId = categoryIdLBL.getText();
//        String name = txtName.getText();
//        int quantity = Integer.parseInt(txtQuantity.getText());
//        double price = Double.parseDouble(txtPrice.getText());
//
//        System.out.println(itemId);
//        System.out.println(categoryId);
//        System.out.println(name);
//        System.out.println(quantity);
//        System.out.println(price);
//
//
//        ItemDTO itemDTO = new ItemDTO(
//                itemId,
//                categoryId,
//                name,
//                quantity,
//                price
//        );
//        try {
//            boolean isSave = itemModel.insertItem(itemDTO);
//            if (isSave) {
//                refeshPage();
//                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Item inserted successfully");
//                alert.showAndWait();
//            }
//        } catch (SQLException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed to save item");
//            alert.showAndWait();
//        }
//    }

    public void homePageOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Mainlayout.fxml"));
        itemAnchorPane.getChildren().clear();
        itemAnchorPane.getChildren().add(load);
    }

    public void newItemOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ItemView.fxml"));
        itemAnchorPane.getChildren().clear();
        itemAnchorPane.getChildren().add(load);
    }

    public void updateItemOnAction(ActionEvent actionEvent) throws IOException {


        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/updateItemView.fxml"));
        newItemAnchorPane.getChildren().clear();
        newItemAnchorPane.getChildren().add(load);

        newItemBtn.setStyle(newItemBtn.getStyle()+ ";-fx-text-fill: White;");
        updateItemBtn.setStyle(newItemBtn.getStyle()+ ";-fx-text-fill: Gold;");
        viewItemBtn.setStyle(viewItemBtn.getStyle()+ ";-fx-text-fill: white;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refeshPage();

        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colOnAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));


    }

    private void refeshPage(){



        cmbBrandname.getSelectionModel().clearSelection();
        txtName.clear();
        txtQuantity.clear();
        txtPrice.clear();



        newItemBtn.setStyle(newItemBtn.getStyle()+ ";-fx-text-fill: Gold;");
        updateItemBtn.setStyle(newItemBtn.getStyle()+ ";-fx-text-fill: white;");
        viewItemBtn.setStyle(viewItemBtn.getStyle()+ ";-fx-text-fill: white;");

        setNextItemId();
        loadNextPaymentId();

        cmbloadBrandName();
        categoryIdLBL.setText("");
        supplierIdLbl.setText("");

        itemTMS.clear();
        tblItem.refresh();

        //  loadTable();



    }

    private void loadNextPaymentId(){
        try {
            paymentIdLbl.setText( itemBO.getNextPaymentID());
            //String nextPaymentId = paymentModel.getNextPaymentId();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed to get next payment id");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setNextItemId(){
        try {
            lblItemId.setText(itemBO.getNextItemId());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed to load ItemId");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void cmbloadBrandName (){
        try {
            ArrayList<String> allBrandNames = itemBO.getAllBrandName();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(allBrandNames);

            cmbBrandname.setItems(observableList);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can not load Brand Names");
            alert.showAndWait();
        }

    }

    public void cmbBrandNameOnAction(ActionEvent actionEvent) {


        try {
            if (cmbBrandname.getSelectionModel().getSelectedItem() != null) {

                Category categoryDTO = itemBO.findByName(cmbBrandname.getSelectionModel().getSelectedItem().toString());

                if (categoryDTO != null) {
                    categoryIdLBL.setText(categoryDTO.getCategory_id());
                    supplierIdLbl.setText(categoryDTO.getSupplier_id());


                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can not find Brand Name");
            alert.showAndWait();
        }


    }

    public void btnAddToCartOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();
        String categoryId = categoryIdLBL.getText();
        String itemName = txtName.getText();
        int qty = Integer.parseInt(txtQuantity.getText());
        Double unitPrice = Double.parseDouble(txtPrice.getText());

        Double totalPrice = unitPrice * qty;

        // Check if the item is already in the cart
//        for (ItemTM itemTM : itemTMS) {
//            if (itemTM.getItemId().equals(itemId)) {
//                int newQty = itemTM.getQty() + qty;
//                itemTM.setQty(newQty);
//                itemTM.setTotalPrice(unitPrice * newQty);
//
//                tblItem.refresh();
//                return;
//            }
//        }

        // Create a "Remove" button
        Button btn = new Button("Remove");

        ItemTM newCartTM = new ItemTM(
                itemId,
                categoryId,
                itemName,
                qty,
                unitPrice,
                totalPrice,
                btn
        );

        // Set remove action for the button
        btn.setOnAction(actionEvent -> {
//            itemTMS.removeIf(item -> item.getItemId().equals(newCartTM.getItemId()));
//            tblItem.refresh();

            itemTMS.remove(newCartTM);
            removeFakeItemId();
            // Refresh the table to reflect the removal of the item.
            tblItem.refresh();

        });

        // Add to the cart

        tblItem.setItems(itemTMS);
        itemTMS.add(newCartTM);

        setFakeItemId();
        clearTxt();


    }

    private void clearTxt(){
        txtName.clear();
        txtQuantity.clear();
        txtPrice.clear();
    }

    private void setFakeItemId(){
        String lastId = lblItemId.getText(); // Last customer ID
        String substring = lastId.substring(1); // Extract the numeric part
        int i = Integer.parseInt(substring); // Convert the numeric part to integer
        int newIdIndex = i + 1; // Increment the number by 1
        lblItemId.setText( String.format("I%03d", newIdIndex));
    }

    private void removeFakeItemId(){
        String lastId = lblItemId.getText(); // Last customer ID
        String substring = lastId.substring(1); // Extract the numeric part
        int i = Integer.parseInt(substring); // Convert the numeric part to integer
        int newIdIndex = i - 1; // Increment the number by 1
        lblItemId.setText( String.format("I%03d", newIdIndex));
    }

    public void btnByItemOnAction(ActionEvent event) {
        if (tblItem.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add items to cart..!").show();
            return;
        }
        Date date = Date.valueOf(dateLbl.getText());
        String supplierId = supplierIdLbl.getText();

        ArrayList<Item> itemDTOS = new ArrayList<>();
        double totalPrice = 0.00;
        for (ItemTM itemTM : itemTMS) {

            // Create order details for each cart item
            Item itemDTO = new Item(
                    itemTM.getItemId(),
                    categoryIdLBL.getText(),
                    itemTM.getItemName(),
                    itemTM.getQty(),
                    itemTM.getPrice()


            );
            totalPrice = (totalPrice + (itemTM.getPrice()));

            // Add to order details array
            itemDTOS.add(itemDTO);
        }

        Payment paymentDTO = new Payment(
                paymentIdLbl.getText(),
                dates,
                totalPrice

        );


        try {
            boolean isSave = itemBO.insertItem(itemDTOS,paymentDTO,supplierId);
            if (isSave) {

                refeshPage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Item inserted successfully");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed to save item");
            alert.showAndWait();
        }

    }

    public void setDate(String userID, Date date) {
        dateLbl.setText(String.valueOf(date));
        userLbl.setText(userID);

        this.dates = date;
        users = userID;


    }

    public void viewItemOnAction(ActionEvent event) {
        try {
            viewItemBtn.setStyle(viewItemBtn.getStyle()+ ";-fx-text-fill: Gold;");
            newItemBtn.setStyle(newItemBtn.getStyle()+ ";-fx-text-fill: white;");
            updateItemBtn.setStyle(newItemBtn.getStyle()+ ";-fx-text-fill: white;");



            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ViewItem.fxml"));
            newItemAnchorPane.getChildren().clear();
            newItemAnchorPane.getChildren().add(load);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Item layout").show();
        }

    }
}
