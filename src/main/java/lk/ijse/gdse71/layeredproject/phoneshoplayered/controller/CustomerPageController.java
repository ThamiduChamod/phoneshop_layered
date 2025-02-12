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
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.CustomerBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.CustomerBOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.CustomerDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Customer;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm.CustomerTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerPageController  implements Initializable {
    public Label lblCustomerId;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtPhone;
    public TableColumn colCustomerId;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colPhone;
    public TableColumn colEmail;
    public TableView tblCustomer;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public AnchorPane customerAnchorPane;


    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    public void resetOnAction(ActionEvent actionEvent) {
        refreshPage();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String customerId = lblCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            try {
                boolean isDelet = customerBO.deleteCustomer(customerId);
                if (isDelet) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);

            }

        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String id = lblCustomerId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        String phonePattern = "^07\\d{8}$";

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

            Customer customerDTO = new Customer(
                    id,
                    name,
                    nic,
                    email,
                    phone
            );
            try {
                boolean isSave = customerBO.updateCustomer(customerDTO);
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

    public void onClickTable(MouseEvent mouseEvent) {
        // CustomerTM customerTM = tblCustomer.getSelectionModel().getSelectedItem();
        CustomerTM customerTM = (CustomerTM) tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTM != null) {
            lblCustomerId.setText(customerTM.getCustomerId());
            txtName.setText(customerTM.getName());
            txtNic.setText(customerTM.getNic());
            txtEmail.setText(customerTM.getEmail());
            txtPhone.setText(customerTM.getPhone());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));


        refreshPage();

    }

    private void refreshPage()  {
        loadNextCustomer();
        loadTableData();

        txtName.clear();
        txtNic.clear();
        txtEmail.clear();
        txtPhone.clear();

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(false);



    }

    private void loadNextCustomer()  {
        try {

            String newcustomerId = customerBO.getNextCustomerId();
            lblCustomerId.setText(newcustomerId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Fail to load customer id").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent)  {

        String id = lblCustomerId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();


        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        String phonePattern = "^07\\d{8}$";


        boolean isValidName = name.matches(namePattern);
        boolean isValidNic =nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);


        if (!isValidName){
            txtName.setStyle(txtName.getStyle() + ";-fx-text-fill: red;");
        }else txtName.setStyle(txtName.getStyle() + ";-fx-text-fill: black;");
        if (!isValidNic){
            txtNic.setStyle(txtNic.getStyle() + ";-fx-text-fill: red;");
        }else  txtNic.setStyle(txtNic.getStyle() + ";-fx-text-fill: black;");
        if (!isValidPhone){
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-text-fill: red;");
        }else txtPhone.setStyle(txtPhone.getStyle() + ";-fx-text-fill: black;");
        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-text-fill: red;");
        }else txtEmail.setStyle(txtEmail.getStyle() + ";-fx-text-fill: black;");

        if (isValidName && isValidEmail && isValidPhone && isValidNic) {

            Customer customerDTO = new Customer(
                    id,
                    name,
                    nic,
                    email,
                    phone
            );
            try {

                boolean isSave = customerBO.saveCustomer(customerDTO);
                if (isSave) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved...!").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Fali to Save Customer...!").show();
                }
            }catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadTableData() {
        try {
            ArrayList<Customer> customerDTOS = customerBO.getAllCustomers();

            ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
            for (Customer customerDTO : customerDTOS) {
                CustomerTM customerTM = new CustomerTM(
                        customerDTO.getCustomerID(),
                        customerDTO.getCustomerName(),
                        customerDTO.getNic(),
                        customerDTO.getCustomerPhone(),
                        customerDTO.getCustomerEmail()
                );
                customerTMS.add(customerTM);
            }
            tblCustomer.setItems(customerTMS);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Fail to load Table Data").showAndWait();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void homeClickOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Mainlayout.fxml"));
        customerAnchorPane.getChildren().clear();
        customerAnchorPane.getChildren().add(load);
    }

    public void generateeportOnAction(ActionEvent event) {

    }

   /* public void generateeportOnAction(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/report/customer_report.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
//           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }


    }*/
}

