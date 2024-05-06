package unilim.info.ihm.td4;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimateRectangleTransition extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Instanciation d'un rectangle.
        Rectangle rect = new Rectangle(50, 50, 150, 100);
        rect.setFill(Color.BLUE);

        Transition transition1 = creerTranslateTransition(rect);
        FadeTransition transition2 = creerFadeTransition(rect);
        //transition1.play(); // On peut lancer l'animation de la translation directement
        //transition2.play(); // On peut lancer l'animation de l'opacité directement

        SequentialTransition st = new SequentialTransition(transition1, transition2);
        ParallelTransition pt = new ParallelTransition(transition1, transition2);

        EcouteurFinTransition ecouteur = new EcouteurFinTransition();
        pt.setOnFinished(ecouteur);

        //st.play(); // On peut lancer l'animation de la translation et de l'opacité en séquentiel (l'un après l'autre)
        pt.play(); // On peut lancer l'animation de la translation et de l'opacité en parallèle (en même temps)

        // Nœud racine
        Group root = new Group();
        root.getChildren().add(rect);

        // Création de la scène.
        Scene scene = new Scene(root, 600, 200);
        scene.setFill(Color.LIGHTGREEN);

        // Affecter la secne à la fenêtre.
        primaryStage.setScene(scene);

        // Titre de la fenêtre.
        primaryStage.setTitle("Animation d'un Rectangle avec Transition");

        // Empecher de modifier la taille.
        primaryStage.setResizable(false);

        // Afficher la fenêtre.
        primaryStage.show();
    }

    private Transition creerTranslateTransition (Rectangle rect) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(4), rect);

        // On peut modifier le type de l'animation
        //tt.setInterpolator(Interpolator.LINEAR);
        tt.setByX(400);
        // On peut mettre des cycles :
        //tt.setCycleCount(3);
        // On peut mettre un auto reverse :
        // tt.setAutoReverse(true);

        return tt;
    }

    private FadeTransition creerFadeTransition (Rectangle rect) {
        FadeTransition ft = new FadeTransition(Duration.seconds(4), rect);

        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        return ft;
    }

    private class EcouteurFinTransition implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // Boite de dialogue de fin d'animation
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setTitle("Fin d'animation");
            dialog.setHeaderText(null);
            dialog.setContentText("L'animation est terminée");
            dialog.show();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
