package unilim.info.ihm.tp5.exo1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AnimateRectangleApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle rectangle = new Rectangle(50, 50, 150, 100);
        rectangle.setFill(Color.BLUE);

        // Création de l'animation.
        AnimationTimer anim = animateRectangle(rectangle);
        // On démarre l'animation au lancement de l'application.
        anim.start();

        // On ajoute le bouton de réinitialisation.
        Button resetButton = new Button("Reset");
        // Lorsque le bouton est cliqué...
        resetButton.setOnMouseClicked(event ->{
            // on revient à la position x = 50
            rectangle.xProperty().set(50);
            // et on redémarre l'animation.
            anim.start();
        });

        Group root = new Group(rectangle, resetButton);
        Scene scene = new Scene(root, 600, 200);
        scene.setFill(Color.LIGHTGREEN);

        // Affecter la secne à la fenêtre.
        primaryStage.setScene(scene);
        // Titre de la fenêtre.
        primaryStage.setTitle("Animation d'un rectangle");

        // Empecher de modifier la taille.
        primaryStage.setResizable(false);

        // Afficher la fenêtre.
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private AnimationTimer animateRectangle(Rectangle rect) {
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (rect.xProperty().get() < 400) {
                    rect.xProperty().set(rect.xProperty().get() + 1);
                } else {
                    stop();
                }
            }
        };

        return animation;
    }
}
