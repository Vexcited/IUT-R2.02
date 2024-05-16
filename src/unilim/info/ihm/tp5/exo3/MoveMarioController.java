package unilim.info.ihm.tp5.exo3;

import javafx.animation.*;
import javafx.beans.value.WritableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Objects;

public class MoveMarioController implements EventHandler<KeyEvent> {
    private final ImageView mario;
    private final int GROUND_Y = 530;
    private final int SCREEN_MAX_X = 1180;
    private String direction = "droite";
    private boolean transitionDone = true;

    public MoveMarioController(ImageView mario) {
        this.mario = mario;
    }

    @Override
    public void handle(KeyEvent event) {
        // On ne peut pas déplacer Mario si une transition est en cours.
        if (!transitionDone) return;
        Timeline timeline = null;

        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q) {
            direction = "gauche";
            double newValue = mario.xProperty().getValue() - 20;

            if (newValue < 0) {
                newValue = 0;
            }

            timeline = moveMario(mario.xProperty(), newValue);
        }
        else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
            direction = "droite";
            double newValue = mario.xProperty().getValue() + 20;

            if (newValue > SCREEN_MAX_X) {
                newValue = SCREEN_MAX_X;
            }

            timeline = moveMario(mario.xProperty(), newValue);
        }
        else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.Z) {
            direction = "haut";
            timeline = moveMario(mario.yProperty(), mario.yProperty().getValue() - 20);
        }
        else if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
            direction = "bas";
            double newValue = mario.yProperty().getValue() + 20;

            if (newValue > GROUND_Y) {
                newValue = GROUND_Y;
            }

            timeline = moveMario(mario.yProperty(), newValue);
        }
        else if (event.getCode() == KeyCode.SPACE) {
            sautTransition();
        }

        if (timeline != null) {
            // On dit qu'une transition est en cours.
            transitionDone = false;

            timeline.play();
            timeline.setOnFinished(e -> transitionDone = true);
        }

        // Load the image according to the direction.
        Image image = ImageLoader.loadMarioImage(direction + ".png", 20, 30);
        mario.setImage(image);
    }

    /**
     * Saut qui va simplement de haut en bas.
     */
    private void sautTransitionStatique () {
        TranslateTransition goToTop = new TranslateTransition(Duration.millis(500), mario);
        goToTop.setByY(-50);

        TranslateTransition goToBottom = new TranslateTransition(Duration.millis(500), mario);
        goToBottom.setByY(50);

        SequentialTransition sequences = new SequentialTransition(goToTop, goToBottom);
        sequences.play();
    }

    /**
     * Saut en fonction de la direction en utilisant des arcs de cercles.
     */
    private void sautTransition () {
        Path chemin = new Path();
        double x = mario.xProperty().get();
        double y = mario.yProperty().get();

        MoveTo elem1 = new MoveTo(x, y);
        ArcTo elem2 = null;
        if ("droite".equals(direction)) {
            elem2 = new ArcTo(50, 50, 0, x+50, y, false, true);
        }
        else if ("gauche".equals(direction)) {
            elem2 = new ArcTo(50, 50, 0, x-50, y, false, false);
        }

        if (elem2 == null) return;
        chemin.getElements().addAll(elem1, elem2);

        PathTransition pathT = new PathTransition(Duration.millis(500), chemin, mario);

        transitionDone = false;
        pathT.play();

        pathT.setOnFinished(e -> {
            transitionDone = true;
            mario.translateYProperty().set(0);
            mario.translateXProperty().set(0);
            if ("droite".equals(direction)) {
                mario.xProperty().set(mario.xProperty().get() + 50);
            }
            else if ("gauche".equals(direction)) {
                mario.xProperty().set(mario.xProperty().get() - 50);
            }
        });
    }

    private Timeline moveMario (WritableValue<Number> prop, Number target) {
        KeyValue kv = new KeyValue(prop, target, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.millis(250), kv);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(kf);

        return timeline;
    }
}
