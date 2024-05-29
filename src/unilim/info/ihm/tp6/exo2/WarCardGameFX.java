package unilim.info.ihm.tp6.exo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class WarCardGameFX extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/WarCardGameView.fxml")));
        Scene scene = new Scene(root);

        primaryStage.setTitle("War Card Game in JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
