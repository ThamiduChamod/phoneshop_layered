package lk.ijse.gdse71.layeredproject.phoneshoplayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.BOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.SignInBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.SigninDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.SigninDAOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SigninDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Signin;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SigninPageController implements Initializable {
    public AnchorPane signInPageAnchorPane;
    public TextField userNameText;
    public TextField nicText;
    public TextField phoneNumberText;
    public TextField emailText;
    public PasswordField passwordText;
    public PasswordField confirmPasswordText;
    public Label userIdLbl;



    SignInBO signInBO = (SignInBO) BOFactory.getInstance().getBO(BOFactory.BOType.SIGNIN);


    public void logInPageOnAction(ActionEvent actionEvent) throws IOException {
        goToLoginPage();
    }

    private void goToLoginPage() throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPageView.fxml"));
        signInPageAnchorPane.getChildren().clear();
        signInPageAnchorPane.getChildren().add(load);
    }

    SigninDAO signinDAO = new SigninDAOImpl();

    public void loadNewUserID()  {

        try {
            String newCustomerId = signInBO.getNextUserID();
            userIdLbl.setText(newCustomerId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void signInOnAction(ActionEvent actionEvent) throws SQLException, IOException {

        String id = userIdLbl.getText();
        String name =userNameText.getText();
        String nic = nicText.getText();
        String phone = phoneNumberText.getText();
        String email =emailText.getText();
        String password = passwordText.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        String phonePattern = "^07\\d{8}$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName){
            userNameText.setStyle(userNameText.getStyle() + ";-fx-text-fill: red;");
        }else userNameText.setStyle(userNameText.getStyle() + ";-fx-text-fill: black;");
        if (!isValidNic){
            nicText.setStyle(nicText.getStyle() + ";-fx-text-fill: red;");
        }else nicText.setStyle(nicText.getStyle() + ";-fx-text-fill: black;");
        if (!isValidEmail){
            emailText.setStyle(emailText.getStyle() + ";-fx-text-fill: red;");
        }else  emailText.setStyle(emailText.getStyle() + ";-fx-text-fill: black;");
        if (!isValidPhone){
            phoneNumberText.setStyle(phoneNumberText.getStyle() + ";-fx-text-fill: red;");
        }else phoneNumberText.setStyle(phoneNumberText.getStyle() + ";-fx-text-fill: black;");


        boolean isAllValidData = isValidName && isValidNic && isValidEmail && isValidPhone;

        if(!(passwordText.getText().equals(confirmPasswordText.getText()))) {
            confirmPasswordText.setStyle("-fx-text-fill: red");
            new Alert(Alert.AlertType.ERROR,"Passwords do not match").show();
            return;
        }
        if (isAllValidData && passwordText.getText().equals(confirmPasswordText.getText()) ){
            SigninDTO userDTO = new SigninDTO(
                    id,
                    name,
                    nic,
                    phone,
                    email,
                    password
            );
            try {
                boolean isSave = signInBO.saveUser(userDTO);
                if (isSave){
                    new Alert(Alert.AlertType.INFORMATION,"User added successfully").show();
                    goToLoginPage();
                }else {
                    new Alert(Alert.AlertType.ERROR,"User not added successfully").show();
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }



        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadNewUserID();
    }
}
