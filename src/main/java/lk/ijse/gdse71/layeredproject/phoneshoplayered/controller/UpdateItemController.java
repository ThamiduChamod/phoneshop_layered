package lk.ijse.gdse71.layeredproject.phoneshoplayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.BOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.UpdateItemBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.UpdateItemBOImpl;
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
import lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm.UpdateItemTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateItemController implements Initializable {
    public ComboBox cmbBrandName;
    public Label brandIdLbl;
    public Label supplierIdLbl;
    public ComboBox cmbItemName;
    public Label itemIdLbl;
    public Label qtyLbl;
    public Label priceLbl;
    public AnchorPane UpdateItemAnchorPane;
    public TextField txtNewQty;
    public Button updateBtn;
    public TableColumn colItemId;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableView tblItems;
    public TableColumn colCategoryId;
    public TableColumn colTotalPrice;
    public TableColumn colOnAcion;
    public Label paymentIdLbl;



    private final ObservableList<UpdateItemTM> updateItemTMS = FXCollections.observableArrayList();


    UpdateItemBO updateItemBO = (UpdateItemBO) BOFactory.getInstance().getBO(BOFactory.BOType.UPDATE_ITEM);



    public void cmbBrandOnAction(ActionEvent actionEvent) {

        if (cmbBrandName.getSelectionModel().getSelectedItem() != null) {

            String selectBarandName = cmbBrandName.getSelectionModel().getSelectedItem().toString();
            System.out.println(selectBarandName);

            try {
                CategoryDTO categoryDTO = updateItemBO.findCategoryByName(selectBarandName);
                if (categoryDTO != null) {

                    brandIdLbl.setText(categoryDTO.getCategory_id());
                    supplierIdLbl.setText(categoryDTO.getSupplier_id());
                    String brandId = categoryDTO.getCategory_id();
                    loadItemName(brandId);

                    cmbBrandName.setDisable(true);
                }
            }catch (SQLException e){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Cannot Load category Data");
                alert.showAndWait();
            }

        }

    }

    private void loadnextPaymentId(){
        try {
            paymentIdLbl.setText(updateItemBO.getNextPaymentID());
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Cannot Load payment ID");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemName(String brandId) {

        try {
            ArrayList<String> allBrandNames = updateItemBO.allItemNameFindByCategoryId(brandId);
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(allBrandNames);

            cmbItemName.setItems(observableList);


        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Cannot Load item Name");
            alert.showAndWait();
        }

    }

    public void cmbItemOnAction(ActionEvent actionEvent) {

        if (cmbBrandName.getSelectionModel().getSelectedItem() != null) {
            if (cmbItemName.getSelectionModel().getSelectedItem() != null ) {
                String selectItemName = cmbItemName.getSelectionModel().getSelectedItem().toString();


                try {
                    ItemDTO itemDTOS = updateItemBO.findByName(selectItemName);


                    itemIdLbl.setText(itemDTOS.getItem_id());
                    qtyLbl.setText(String.valueOf(itemDTOS.getQty()));
                    priceLbl.setText(String.valueOf(itemDTOS.getPrice()));

                    //txtNewQty.setDisable(false);


                    txtNewQty.setDisable(false);

                    updateBtn.setDisable(false);


                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot Load item Data");
                    alert.showAndWait();
                }
            }

        }

    }

    public void updateOnAction(ActionEvent mouseEvent) {


        if (tblItems.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add items to cart..!").show();
            return;
        }
        Date date = Date.valueOf(LocalDate.now().toString());
        String supplierId = supplierIdLbl.getText();

        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        double totalPrice = 0.00;
        for (UpdateItemTM itemTM : updateItemTMS) {

            // Create order details for each cart item
            ItemDTO itemDTO = new ItemDTO(
                    itemTM.getItemId(),
                    brandIdLbl.getText(),
                    itemTM.getItemName(),
                    itemTM.getQuantity(),
                    itemTM.getUnitPrice()


            );
            totalPrice = (totalPrice + (itemTM.getUnitPrice()));

            // Add to order details array
            itemDTOS.add(itemDTO);
        }

        PaymentDTO paymentDTO = new PaymentDTO(
                paymentIdLbl.getText(),
                date,
                totalPrice

        );


        try {
            boolean isSave = updateItemBO.updateItem(itemDTOS,paymentDTO,supplierId);
            if (isSave) {
                cmbBrandName.setDisable(false);
                refreshPage();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Item update successfully");
                alert.showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail Item Update").showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed to save item");
            alert.showAndWait();
        }

    }

    public void resetOnAction(ActionEvent actionEvent) {
        refreshPage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshPage();

        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colOnAcion.setCellValueFactory(new PropertyValueFactory<>("removeButton"));
    }

    private void refreshPage (){

        loadnextPaymentId();

        cmbBrandName.getSelectionModel().clearSelection();
        brandIdLbl.setText("");
        supplierIdLbl.setText("");

        cmbItemName.getSelectionModel().clearSelection();
        itemIdLbl.setText("");
        qtyLbl.setText("");
        priceLbl.setText("");

        getAllBarndName();
        updateBtn.setDisable(true);
        //loadTable();

        cmbBrandName.getSelectionModel().clearSelection();
        cmbItemName.getSelectionModel().clearSelection();
        txtNewQty.clear();
        txtNewQty.setDisable(true);

        cmbBrandName.setDisable(false);
        updateItemTMS.clear();
        tblItems.refresh();

    }


    private void getAllBarndName(){
        try {
            ArrayList<String> allBrandNames = updateItemBO.getAllBrandNames();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(allBrandNames);

            cmbBrandName.setItems(observableList);


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can not Load Barnd Names");
            alert.showAndWait();
        }

    }

    public void btnAddToCartOnAction(ActionEvent event) {

        String itemId = itemIdLbl.getText();
        String categoryId = brandIdLbl.getText();
        String selectedItemName = cmbItemName.getSelectionModel().getSelectedItem().toString();
        int qty = Integer.parseInt(txtNewQty.getText());
        double unitPrice = Double.parseDouble(priceLbl.getText());
        double totalPrice = unitPrice * qty;

        Button btn = new Button("Remove");

        UpdateItemTM newCartTM = new UpdateItemTM(

                categoryId,
                itemId,
                selectedItemName,
                qty,
                unitPrice,
                totalPrice,
                btn
        );

        // Set remove action for the button
        btn.setOnAction(actionEvent -> {
//            itemTMS.removeIf(item -> item.getItemId().equals(newCartTM.getItemId()));
//            tblItem.refresh();

            updateItemTMS.remove(newCartTM);

            // Refresh the table to reflect the removal of the item.
            tblItems.refresh();

        });

        // Add to the cart

        tblItems.setItems(updateItemTMS);
        updateItemTMS.add(newCartTM);

        cmbItemName.getSelectionModel().clearSelection();
        itemIdLbl.setText("");
        qtyLbl.setText("");
        priceLbl.setText("");
        txtNewQty.clear();



    }


}
