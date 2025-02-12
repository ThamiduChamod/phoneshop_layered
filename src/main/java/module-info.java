module lk.ijse.gdse71.layeredproject.phoneshoplayered {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens lk.ijse.gdse71.layeredproject.phoneshoplayered.controller to javafx.fxml;
    opens lk.ijse.gdse71.layeredproject.phoneshoplayered.view.tdm to javafx.base;
    exports lk.ijse.gdse71.layeredproject.phoneshoplayered;
}