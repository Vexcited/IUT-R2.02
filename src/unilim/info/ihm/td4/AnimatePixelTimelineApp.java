package unilim.info.ihm.td4;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class AnimatePixelTimelineApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Nœud racine
        StackPane root = new StackPane();

        // Créer l'image de fond.
        Image img = new Image(Objects.requireNonNull(getClass().getResource("football-playground.jpg")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1, 1, true, true, true, false)
        );

        Background bg = new Background(backgroundImage);
        root.setBackground(bg); // Affecter l'image de fond à la racine.

        ImageView perso = chargerPersonnage();
        MovePixelController ctrl = new MovePixelController(perso);
        root.getChildren().add(perso);

        Timeline timeline = animerPersonnage(perso);
        //timeline.play();

        // Création de la scène.
        Scene scene = new Scene(root, 800, 600);
        scene.setOnKeyPressed(ctrl);

        // Affecter la secne à la fenêtre.
        primaryStage.setScene(scene);

        primaryStage.setTitle("Animation d'un personnage avec Timeline");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private ImageView chargerPersonnage() {
        Image img = new Image(
            Objects.requireNonNull(getClass().getResource("personnage.png")).toExternalForm(),
            80,
            80,
            true,
            false,
            false
        );

        ImageView imgView = new ImageView(img);
        return imgView;
    }

    private Timeline animerPersonnage (ImageView pers) {
        KeyValue kv = new KeyValue(pers.translateXProperty(), pers.translateXProperty().get() + 250, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(kf);

        // ça marche aussi
        //timeline.setCycleCount(2);
        //timeline.setAutoReverse(true);

        return timeline;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
