package unilim.info.ihm.td4;

import javafx.animation.*;
import javafx.beans.value.WritableValue;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class MovePixelController implements EventHandler<KeyEvent> {
    private ImageView pixel;
    public MovePixelController(ImageView pixel) {
        this.pixel = pixel;
    }

    @Override
    public void handle(KeyEvent event) {
        Timeline timeline = null;

        if (event.getCode() == KeyCode.LEFT) {
            timeline = deplacerPersonnage(pixel.translateXProperty(), pixel.translateXProperty().getValue() - 10);
        }
        else if (event.getCode() == KeyCode.RIGHT) {
            timeline = deplacerPersonnage(pixel.translateXProperty(), pixel.translateXProperty().getValue() + 10);
        }
        else if (event.getCode() == KeyCode.UP) {
            timeline = deplacerPersonnage(pixel.translateYProperty(), pixel.translateYProperty().getValue() - 10);
        }
        else if (event.getCode() == KeyCode.DOWN) {
            timeline = deplacerPersonnage(pixel.translateYProperty(), pixel.translateYProperty().getValue() + 10);
        }
        else if (event.getCode() == KeyCode.SPACE) {
            sautEnArcDeCercle();
        }

        if (timeline != null) timeline.play();
    }

    private void sautEnArcDeCercle() {
        // Création du chemin
        Path chemin = new Path();
        MoveTo elem1 = new MoveTo(pixel.translateXProperty().get(), pixel.translateYProperty().get());
        ArcTo elem2 = new ArcTo(10, 10, 0, pixel.translateXProperty().get() + 70 + 40, pixel.translateYProperty().get() + 40, true, false);

        chemin.getElements().addAll(elem1, elem2);

        PathTransition pathT = new PathTransition(Duration.millis(500), chemin, pixel);
        pathT.play();
    }

    private Timeline deplacerPersonnage (WritableValue<Number> prop, Number target) {
        KeyValue kv = new KeyValue(prop, target, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.millis(250), kv);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(kf);

        return timeline;
    }
}
