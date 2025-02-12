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
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.OrderPaymentBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.OrderPaymentBOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.*;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.*;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.ItemDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.OrderDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.OrderDetailsDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.PaymentDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Item;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Order;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.OrderDetails;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Payment;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm.CartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderPaymentPageController implements Initializable {


    public AnchorPane oderPaymentAnchorPane;
    public Label paymentIdLbl;
    public ComboBox customerNameCmb;
    public Label customerIdLbl;
    public ComboBox brandNameCmb;
    public ComboBox itemName;
    public Label itemIdLbl;
    public Label unitPriceLbl;
    public Label qtyLbl;
    public TextField txtAddToCartQty;
    public TextField txtAddToCartDiscount;
    public Button addToCartBtn;
    public TableView tblCart;
    public TableColumn colItemId;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableColumn colTotal;
    public TableColumn colAction;
    public Button paceOrderBtn;
    public Label brandIdLbl;
    public ComboBox itemNameCmb;
    public TableColumn colBrandName;
    public Label oderIdLbl;
    public ComboBox methodCmb;
    public Label dateLbl;
    public Label userLbl;
    public TableColumn colBrandId;
    public TableColumn colItemName;
    public TableColumn colUnitPrice;
    public TableColumn colDIiscount;
    public TableColumn colMethord;
    public TableColumn colHandOnQty;



    OrderPaymentBO orderPaymentBO = (OrderPaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER_PAYMENT);

    private final ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshPage();
        setCellValues();

    }

    private void refreshPage (){


        setNextOrderId();
        setNextPaymentId();
        loadCustomerNames();
        setPaymentMethods();

        cartTMS.clear();
        tblCart.refresh();
        addToCartBtn.setDisable(true);
    }

    private void clearAllText(){
        customerNameCmb.getSelectionModel().clearSelection();
        customerIdLbl.setText("");
        brandNameCmb.getSelectionModel().clearSelection();
        brandIdLbl.setText("");
        itemNameCmb.getSelectionModel().clearSelection();
        itemIdLbl.setText("");
        unitPriceLbl.setText("");
        qtyLbl.setText("");
        txtAddToCartQty.setText("");
        txtAddToCartDiscount.setText("0");
        addToCartBtn.setDisable(true);

    }

    private void cartBtnClearTxt(){
        brandNameCmb.getSelectionModel().clearSelection();
        brandIdLbl.setText("");
        itemNameCmb.getSelectionModel().clearSelection();
        itemIdLbl.setText("");
        unitPriceLbl.setText("");
        qtyLbl.setText("");
        txtAddToCartQty.setText("");
        txtAddToCartDiscount.setText("0");
        addToCartBtn.setDisable(true);

    }

    private void setCellValues() {

        colBrandId.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colHandOnQty.setCellValueFactory(new PropertyValueFactory<>("handOnqty"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQuantity"));
        colDIiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colMethord.setCellValueFactory(new PropertyValueFactory<>("method"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));


        tblCart.setItems(cartTMS);
    }

    private void setNextOrderId(){
        try {
            String oderId = orderPaymentBO.getNextOrderID();
            oderIdLbl.setText(oderId);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed to load Order ID");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setPaymentMethods(){
        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Card","Cash");
        methodCmb.setItems(paymentMethods);
        methodCmb.getSelectionModel().selectFirst();
    }

    private void setNextPaymentId(){
        try {
            paymentIdLbl.setText(orderPaymentBO.getNextPaymentID());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to Load PaymentId");
            alert.showAndWait();
        }
    }

    private void loadCustomerNames(){
        try {
            ArrayList<String> arrayList = orderPaymentBO.getAllCustomerNames();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(arrayList);

            customerNameCmb.setItems(observableList);

        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to Load Customer Name");
            alert.showAndWait();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void cmbmethoddOnAction(ActionEvent event) {

    }

    public void cmbCustomerNameOnAction() {


        try {
            if (customerNameCmb.getSelectionModel().getSelectedItem()!= null) {
                String selectedCustomerName  = customerNameCmb.getSelectionModel().getSelectedItem().toString();

                customerIdLbl.setText(orderPaymentBO.getCustomerIdFindByName(selectedCustomerName));
                loadBrandNames();
            }
            return;

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to Load Customer Id");
            alert.showAndWait();
        }

    }

    private void loadBrandNames(){
        try {
            ArrayList<String> arrayList = orderPaymentBO.getAllBrandNames();
            ObservableList<String> allNames = FXCollections.observableArrayList();
            allNames.addAll(arrayList);

            brandNameCmb.setItems(allNames);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to Load Brand Name");
            alert.showAndWait();
        }

    }

    public void cmbBrandOnAction(ActionEvent actionEvent) {


        try {
            if (brandNameCmb.getSelectionModel().getSelectedItem()!= null) {
                String selectedBrandName = brandNameCmb.getSelectionModel().getSelectedItem().toString();
                brandIdLbl.setText(orderPaymentBO.getBrandIdFindByName(selectedBrandName));
                loadItemName(brandIdLbl.getText());
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to Load Brand ID");
            alert.showAndWait();
        }

    }

    public void loadItemName(String brandId){
        try {
            ArrayList<String> arrayList= orderPaymentBO.allItemNameFindByCategoryId(brandId);
            ObservableList<String> allNames = FXCollections.observableArrayList();
            allNames.addAll(arrayList);

            itemNameCmb.setItems(allNames);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to Load Item Name");
            alert.showAndWait();
        }
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {


        if (itemNameCmb.getSelectionModel().getSelectedItem()!=null){
            try {
                String selectedItemName = itemNameCmb.getSelectionModel().getSelectedItem().toString();
                Item itemDTO = orderPaymentBO.findByName(selectedItemName);

                itemIdLbl.setText(itemDTO.getItem_id().toString());
                unitPriceLbl.setText(String.valueOf(itemDTO.getPrice()));
                qtyLbl.setText(String.valueOf(itemDTO.getQty()));

                addToCartBtn.setDisable(false);

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to Load Item Data");
                alert.showAndWait();
            }
        }

    }

    public void homeClickOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Mainlayout.fxml"));
        oderPaymentAnchorPane.getChildren().clear();
        oderPaymentAnchorPane.getChildren().add(load);

    }

    public void btnAddToCartOnAction(ActionEvent event) {
        if (txtAddToCartQty.getText() ==""|| txtAddToCartQty.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Please enter a quantity");
            alert.showAndWait();
            return;
        }


        int qty = Integer.parseInt(qtyLbl.getText());
        int cartQty = Integer.parseInt(txtAddToCartQty.getText());


        if (qty >= cartQty){


            String brandId = brandIdLbl.getText();
            double unitPrice = Double.parseDouble(unitPriceLbl.getText());
            int discount = Integer.parseInt(txtAddToCartDiscount.getText());
            double total = unitPrice * cartQty -((unitPrice * discount) /100);
            String itemId = itemIdLbl.getText();
            String selectedItemName = itemNameCmb.getSelectionModel().getSelectedItem().toString();
            String selectedBrandName = brandNameCmb.getSelectionModel().getSelectedItem().toString();
            int handOnQty = Integer.parseInt(qtyLbl.getText());
            String methord = methodCmb.getSelectionModel().getSelectedItem().toString();



            for (CartTM cartTM : cartTMS) {

                // Check if the item is already in the cart
                if (cartTM.getItemId().equals(itemIdLbl.getText())) {
                    // Update the existing CartTM object in the cart's observable list with the new quantity and total.
                    int newQty = cartTM.getCartQuantity() + cartQty;
                    cartTM.setCartQuantity(newQty); // Add the new quantity to the existing quantity in the cart.
                    cartTM.setTotal(unitPrice * newQty); // Recalculate the total price based on the updated quantity

                    // Refresh the table to display the updated information.
                    tblCart.refresh();
                    return; // Exit the method as the cart item has been updated.
                }
            }
            Button btn = new Button("Remove");
            btn.setStyle("-fx-background-color: white; -fx-text-fill: red; -fx-font-weight: bold;");


            // If the item does not already exist in the cart, create a new CartTM object to represent it.
            CartTM newCartTM = new CartTM(
                    brandId,
                    selectedBrandName,
                    itemId,
                    selectedItemName,
                    unitPrice,
                    handOnQty,
                    cartQty,
                    discount,
                    methord,
                    total,
                    btn
            );

            // Set an action for the "Remove" button, which removes the item from the cart when clicked.
            btn.setOnAction(actionEvent -> {

                // Remove the item from the cart's observable list (cartTMS).
                cartTMS.remove(newCartTM);

                // Refresh the table to reflect the removal of the item.
                tblCart.refresh();
            });

            // Add the newly created CartTM object to the cart's observable list.
            cartTMS.add(newCartTM);



            cartBtnClearTxt();


        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Not Enough Items");
            alert.showAndWait();
        }




    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        refreshPage();
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        if (tblCart.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add items to cart..!").show();
            return;
        }

        String oderId = oderIdLbl.getText();
        String c_id = customerIdLbl.getText();
        String pay_id = paymentIdLbl.getText();
        String itemId = itemIdLbl.getText();
        Date date = Date.valueOf(dateLbl.getText());

        // Create a new list to avoid concurrent modification
        ArrayList<OrderDetails> orderDetailsDTOS = new ArrayList<>();
        double total = 0.00 ;

        for (CartTM cartTM : cartTMS) {
            total = total + cartTM.getTotal();

            OrderDetails orderDetailsDTO = new OrderDetails(
                    oderId,
                    cartTM.getItemId(),
                    cartTM.getCartQuantity(),
                    cartTM.getTotal()


            );
            orderDetailsDTOS.add(orderDetailsDTO);
        }
        Payment paymentDTO = new Payment(
                pay_id,
                date,
                total
        );
        Order orderDTO = new Order(
                oderId,
                date,
                pay_id,
                c_id,
                orderDetailsDTOS
        );
        try {
            boolean isSave = orderPaymentBO.saveOrder(paymentDTO,orderDTO);
            if (isSave) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully saved Order");
                alert.showAndWait();
                refreshPage();
                clearAllText();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Fail to save Orderrrr");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Fail to save Order");
            alert.showAndWait();
        }

    }

    public void setDate(String user, Date date) {
        userLbl.setText(user);
        dateLbl.setText(String.valueOf(date));
    }
}

