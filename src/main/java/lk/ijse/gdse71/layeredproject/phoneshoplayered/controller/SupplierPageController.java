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
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.SupplierBo;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.SupplierBOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.SupplierDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SupplierDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Supplier;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm.SupplierTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierPageController implements Initializable {
    public AnchorPane supplierAnchorPane;
    public Label supllierLbl;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtPhone;
    public Button btnDelete;
    public Button btnUpdate;
    public TableColumn colSupplierId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colPhone;
    public TableColumn colEmail;
    public Button btnSave;
    public TableView tblSupplier;
    public TextField txtBrand;


    SupplierBo supplierBo = (SupplierBo) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = supllierLbl.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();


        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName){
            txtName.setStyle(txtName.getStyle() + ";-fx-text-fill: red;");
        }
        if (!isValidNic){
            txtNic.setStyle(txtNic.getStyle() + ";-fx-text-fill: red;");
        }
        if (!isValidPhone){
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-text-fill: red;");
        }
        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-text-fill: red;");
        }

        boolean validAlldata = isValidName && isValidNic && isValidEmail && isValidPhone;

        if (validAlldata){

            Supplier supplierDTO = new Supplier(
                    id,
                    name,
                    nic,
                    email,
                    phone

            );
            try {
                boolean isSave = supplierBo.saveSupplier(supplierDTO,txtBrand.getText());
                if (isSave) {
                    refreshPage();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Save Supplier Successfully & Add Category");
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Save Supplier & Category Failed");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Failed to load Category ID");
                alert.showAndWait();
            }
        }

    }

    public void homeClickOnAction(MouseEvent mouseEvent) throws IOException {

        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Mainlayout.fxml"));
        supplierAnchorPane.getChildren().clear();
        supplierAnchorPane.getChildren().add(load);

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {


        String id = supllierLbl.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName){
            txtName.setStyle(txtName.getStyle() + ";-fx-text-fill: red;");
        }
        if (!isValidNic){
            txtNic.setStyle(txtNic.getStyle() + ";-fx-text-fill: red;");
        }
        if (!isValidPhone){
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-text-fill: red;");
        }
        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-text-fill: red;");
        }

        boolean validAlldata = isValidName && isValidNic && isValidEmail && isValidPhone;

        if (validAlldata) {

            Supplier supplierDTO = new Supplier(
                    id,
                    name,
                    nic,
                    email,
                    phone
            );
            try {


                boolean isSave = supplierBo.updateSupplier(supplierDTO);
                if (isSave) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Update...!").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to Update Customer...!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Fail to Update Customer...!").show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String supplierId = supllierLbl.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            try {


                boolean isDelete = supplierBo.deleteSupplier(supplierId);
                if (isDelete) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete customer & Category...!").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Fail to delete customer & Category...!").show();
            }
        }



    }

    public void onClickTable(MouseEvent mouseEvent) {

        SupplierTM supplierTM = (SupplierTM) tblSupplier.getSelectionModel().getSelectedItem();
        if (supplierTM != null) {
            supllierLbl.setText(supplierTM.getSupplierId());
            txtName.setText(supplierTM.getName());
            txtNic.setText(supplierTM.getNic());
            txtEmail.setText(supplierTM.getEmail());
            txtPhone.setText(supplierTM.getPhone());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

    public void resetOnAction(ActionEvent actionEvent) {
        refreshPage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        refreshPage();
    }

    private void refreshPage () {
        loadAllSupplier();
        loadNextSupplierId();
        loadAllSupplier();

        txtName.clear();
        txtNic.clear();
        txtEmail.clear();
        txtPhone.clear();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);


    }

    private void loadNextSupplierId() {
        try {
            String nextSupplierId = supplierBo.getNextSupplierID();
            supllierLbl.setText(nextSupplierId);
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to load Supplier Id");
            alert.show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllSupplier (){
        try {
            ArrayList<Supplier> supplierDTOS = supplierBo.getAllSupplier();
            ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();

            for (Supplier supplierDTO : supplierDTOS) {
                SupplierTM supplierTM = new SupplierTM(
                        supplierDTO.getSupplier_id(),
                        supplierDTO.getName(),
                        supplierDTO.getNic(),
                        supplierDTO.getEmail(),
                        supplierDTO.getPhone()
                );
                supplierTMS.add(supplierTM);
            }
            tblSupplier.setItems(supplierTMS);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fail to load Table ");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void generateReportOnAction(ActionEvent event) {

//        try {
//            JasperReport jasperReport = JasperCompileManager.compileReport(
//                    getClass()
//                            .getResourceAsStream("/report/supplier_report.jrxml"
//                            ));
//
//            Connection connection = DBConnection.getInstance().getConnection();
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(
//                    jasperReport,
//                    null,
//                    connection
//            );
//
//            JasperViewer.viewReport(jasperPrint, false);
//
//        } catch (JRException e) {
//            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
////           e.printStackTrace();
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
//        }
//


    }
}
