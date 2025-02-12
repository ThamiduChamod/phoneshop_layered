package lk.ijse.gdse71.layeredproject.phoneshoplayered.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;

public class PaymentPageController  {
    public AnchorPane paymentAnchorPane;
    private String user;
    private Date date;

    public void setDate(String user, Date date) {
        this.user = user;
        this.date = date;
    }

    public void supplierOnAction(MouseEvent mouseEvent) {
    }

    public void plaseOrderOnAction(MouseEvent mouseEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderPayment.fxml"));
        Parent load = loader.load(); // Load and get the root node

        OrderPaymentPageController controller = loader.getController();

        controller.setDate(user,date);

        paymentAnchorPane.getChildren().clear();
        paymentAnchorPane.getChildren().add(load);

//        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OrderPayment.fxml"));
//        paymentAnchorPane.getChildren().clear();
//        paymentAnchorPane.getChildren().add(load);
    }
}

