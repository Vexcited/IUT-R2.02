package unilim.info.ihm.tp1.exo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestJavaFX extends Application {
  @Override
  public void start(Stage primaryStage) {
    // On initialise les composants.
    StackPane layout = new StackPane();
    Button button = new Button("Valider");

    // On ajoute le bouton au layout.
    layout.getChildren().add(button);

    // On ajoute le layout à la scène.
    Scene scene = new Scene(layout, 600, 250);

    // On ajoute la scène à la fenêtre.
    primaryStage.setTitle("Première fenêtre");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}