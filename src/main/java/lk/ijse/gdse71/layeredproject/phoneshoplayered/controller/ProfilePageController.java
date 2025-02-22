package lk.ijse.gdse71.layeredproject.phoneshoplayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.BOFactory;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.bo.custom.ProfileBO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.dto.SigninDTO;
import lk.ijse.gdse71.layeredproject.phoneshoplayered.entity.Signin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ProfilePageController {
    public AnchorPane profileAnchorPane;
    public Label nameLbl;
    public Label nicLbl;
    public Label phoneLbl;
    public Label emailLbl;
    public PasswordField NewPasswordText;
    public PasswordField confirmPasswordText;
    public PasswordField OldPasswordText;
    public Label userIdLbl;


    ProfileBO profileBO =(ProfileBO) BOFactory.getInstance().getBO(BOFactory.BOType.PROFILE);

    public void homePageOnAction(MouseEvent mouseEvent) throws IOException {

        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Mainlayout.fxml"));
        profileAnchorPane.getChildren().clear();
        profileAnchorPane.getChildren().add(load);
    }

    public void passwordResetBtn(ActionEvent actionEvent) {

        try {
            String oldPassWord = profileBO.getOldPassword(userIdLbl.getText());

            System.out.println(oldPassWord);

            if (oldPassWord.equals(OldPasswordText.getText())) {

                if (confirmPasswordText.getText().equals(NewPasswordText.getText())) {
                    System.out.println("all done");
                    System.out.println(userIdLbl.getText());
                    System.out.println(NewPasswordText.getText());


                    Signin signinDTO = new Signin();
                    signinDTO.setUserID(userIdLbl.getText());
                    signinDTO.setPassword(NewPasswordText.getText());
                    boolean isUpdate = profileBO.signInUpdate(signinDTO);

                    if (isUpdate) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Password Reset Successful");
                        alert.showAndWait();
                        refreshPage();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Password Reset Failed").show();
                    }

                } else {
                    NewPasswordText.setStyle(NewPasswordText.getStyle() + ";-fx-text-fill: red;");
                    confirmPasswordText.setStyle(confirmPasswordText.getStyle() + ";-fx-text-fill: red;");
                }
            } else {
                OldPasswordText.setStyle(OldPasswordText.getStyle() + ";-fx-text-fill: red;");
                new Alert(Alert.AlertType.ERROR, "Wrong passWord").show();
            }


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Old password").show();
        } catch (ClassNotFoundException e) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void DeleteAccountBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            try {
                boolean isDeleted = profileBO.deleteUser(userIdLbl.getText());
                if (isDeleted) {

                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted...!").show();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPageView.fxml"));
                    profileAnchorPane.getChildren().clear();
                    profileAnchorPane.getChildren().add(load);

                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete customer...!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete customer...!").show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void setData(String userId){
        userIdLbl.setText(userId);
        System.out.println("Setting data for userId: " + userId); // Debug output

        try {
            SigninDTO userDetails = profileBO.getAllUsingID(userId);

            nameLbl.setText(userDetails.getUserName());
            nicLbl.setText(userDetails.getNic());
            phoneLbl.setText(userDetails.getPhoneNumber());
            emailLbl.setText(userDetails.getEmail());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load user details!").show();
            e.printStackTrace(); // Print stack trace to identify the error
        }


    }

   public void refreshPage() throws IOException {

       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfileView.fxml"));
       Parent load = loader.load(); // Load and get the root node

       ProfilePageController controller = loader.getController();
       controller.setData(userIdLbl.getText());

       profileAnchorPane.getChildren().clear();
       profileAnchorPane.getChildren().add(load);


   }
}
