package unilim.info.ihm.tp4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginAppSb extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("vue/LoginForm.fxml")));
        Scene scene = new Scene(root, 600, 170);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("vue/login.css")).toExternalForm());

        primaryStage.setTitle("Application Login avec Scene Builder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
