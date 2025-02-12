package lk.ijse.gdse71.layeredproject.phoneshoplayered;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/LoginPageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Z Mobile");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/Logo.jpg"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}