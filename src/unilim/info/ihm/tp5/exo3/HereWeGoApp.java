package unilim.info.ihm.tp5.exo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class HereWeGoApp extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        Image defaultMarioImage = ImageLoader.loadMarioImage("droite.png", 20, 30);
        ImageView mario = new ImageView(defaultMarioImage);
        mario.setX(14);
        mario.setY(530);

        Image backgroundLoadedImage = new Image(Objects.requireNonNull(getClass().getResource("niveau.jpg")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
            backgroundLoadedImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1, 1, true, true, true, false)
        );

        Background bg = new Background(backgroundImage);
        root.setBackground(bg); // Affecter l'image de fond à la racine.

        root.getChildren().add(mario);

        Scene scene = new Scene(root, 1200, 622);
        primaryStage.setScene(scene);

        scene.setOnKeyPressed(new MoveMarioController(mario));

        primaryStage.setTitle("Here we go");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
