package lk.ijse.gdse71.layeredproject.phoneshoplayered.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public AnchorPane mainLayout;
    public Label mouseEnterLbl;
    public Label userIdLbl;

    private static String userID;
    public Label dateLbl;
    public Label timeLbl;
    public Pane mainPane;
    public Pane btnPane;



    public void gotoProfileOnAction(MouseEvent mouseEvent) throws IOException, SQLException {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfileView.fxml"));
            Parent load = loader.load(); // Load and get the root node

            ProfilePageController controller = loader.getController();
            controller.setData(userID);

            mainLayout.getChildren().clear();
            mainLayout.getChildren().add(load);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mouseEntredOnAction(MouseEvent mouseEvent) {
        mouseEnterLbl.setText("click go to profile");
    }

    public void mouseExitedOnAction(MouseEvent mouseEvent) {
        mouseEnterLbl.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnPane.setVisible(true);
        mainPane.setVisible(false);
        userIdLbl.setText(userID);
        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> setDateAndTime()));
        clock.setCycleCount(Timeline.INDEFINITE); // Keep it running indefinitely
        clock.play();


    }

    public void setData(String userId) {
        userIdLbl.setText(userId);
        userID = userId;
    }

    public void setDateAndTime() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(timeFormatter);

        dateLbl.setText(formattedDate);
        timeLbl.setText(formattedTime);
    }

    public void logOutBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPageView.fxml"));
        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(load);
    }

    public void menuBarOnAction(MouseEvent mouseEvent) {
        mainPane.setVisible(true);
        btnPane.setVisible(false);

    }

    public void menuBarMouseEntered(MouseEvent mouseEvent) {
        mainPane.setVisible(true);
    }

    public void menuBarMouseExited(MouseEvent mouseEvent) {
        mainPane.setVisible(false);
        btnPane.setVisible(true);
    }

    public void addCustomerOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"));
        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(load);
    }

    public void addItemOnAction(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ItemView.fxml"));
        Parent load = loader.load(); // Load and get the root node

        ItemPageController controller = loader.getController();
        Date date = Date.valueOf(dateLbl.getText());
        controller.setDate(userID,date);

        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(load);


//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ItemView.fxml"));
//        mainLayout.getChildren().clear();
//        mainLayout.getChildren().add(load);
    }

    public void menuMouseEntered(MouseEvent mouseEvent) {
        mainPane.setVisible(true);
        btnPane.setVisible(false);
    }

    public void supplierOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SupplierView.fxml"));
        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(load);
    }

    public void paymentOnAction(MouseEvent mouseEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PaymentView.fxml"));
        Parent load = loader.load(); // Load and get the root node

        PaymentPageController controller = loader.getController();
        Date date = Date.valueOf(dateLbl.getText());
        controller.setDate(userID,date);

        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(load);


    }

}
