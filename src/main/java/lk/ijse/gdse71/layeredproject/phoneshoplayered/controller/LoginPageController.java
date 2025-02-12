package lk.ijse.gdse71.layeredproject.phoneshoplayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.BOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.LoginBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.impl.LoginBOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.LoginDAO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dao.custom.impl.LoginDAOImpl;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.LoginDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Login;

import java.io.IOException;
import java.util.ArrayList;

public class LoginPageController {
    public AnchorPane loginLayout;
    public PasswordField pssswoardTxt;
    public TextField userIdTxt;



    LoginBO loginBO = (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);


    public void loginBtnClick() {
        try {
            ArrayList<Login> logInDTOS = loginBO.getAllLogins();

            for (Login loginDTO : logInDTOS){
                System.out.println(loginDTO.getPassword());
                System.out.println(loginDTO.getUserID());
                if(loginDTO.getUserID().equals(userIdTxt.getText()) && loginDTO.getPassword().equals(pssswoardTxt.getText())){

                    goToMainpage(loginDTO.getUserID());
                    return;
                }

            }
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password ");
            alert.showAndWait();
            reloadPage();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Cannot load Id and Password");
            alert.showAndWait();
        }






    }

    private void reloadPage() throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPageView.fxml"));
        loginLayout.getChildren().clear();
        loginLayout.getChildren().add(load);
    }

    private void goToMainpage(String userID) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainLayout.fxml"));
            Parent load = loader.load(); // Load and get the root node

            MainPageController controller = loader.getController();
            controller.setData(userID);

            loginLayout.getChildren().clear();
            loginLayout.getChildren().add(load);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can not load MainLayout ");
            alert.showAndWait();
        }


    }

    public void signInPageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SignInPageView.fxml"));
        loginLayout.getChildren().clear();
        loginLayout.getChildren().add(load);
    }
}
