package unilim.info.ihm.tp2.exo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unilim.info.ihm.tp2.exo2.model.RootPane;

public class FormInscriptionFX extends Application {  
  @Override
  public void start(Stage primaryStage) {
    RootPane root = new RootPane();

    Scene scene = new Scene(root, 600, 450);    
    primaryStage.setTitle("Application Newsletter");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}