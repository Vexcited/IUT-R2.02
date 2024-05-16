package unilim.info.ihm.tp5.exo2;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MoveRectangleController implements EventHandler<KeyEvent> {
    private final Rectangle rectangle;
    public MoveRectangleController(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void handle(KeyEvent event) {
        Timeline timeline = null;
        DoubleProperty propertyX = rectangle.xProperty();

        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.Q) {
            double newX = propertyX.getValue() - 10;

            // On évite de sortir du cadre.
            if (newX > 50) {
                timeline = this.moveRectangle(propertyX, newX);
            }
        }
        else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
            double newX = propertyX.getValue() + 10;

            // On évite de sortir du cadre.
            if (newX < 400) {
                timeline = this.moveRectangle(propertyX, newX);
            }
        }

        if (timeline != null) timeline.play();
    }

    private Timeline moveRectangle(DoubleProperty prop, double target) {
        KeyValue kv = new KeyValue(prop, target, Interpolator.LINEAR);
        KeyFrame kf = new KeyFrame(Duration.millis(100), kv);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(kf);

        return timeline;
    }
}
