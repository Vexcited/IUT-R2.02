package unilim.info.ihm.td4;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AnimateRectangleTimerApp extends Application {
    private Integer nbFrame = 0;
    private Label lblNbFrame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Instanciation d'un rectangle.
        Rectangle rect = new Rectangle(50, 50, 150, 100);
        rect.setFill(Color.BLUE);

        // Instanciation d'un label.
        HBox labelNbFrame = creerLabelNbFrame();

        // Création de l'animation.
        AnimationTimer anim = animerRectangle(rect);
        anim.start();

        // Nœud racine
        Group root = new Group();
        root.getChildren().addAll(rect, labelNbFrame);

        // Création de la scène.
        Scene scene = new Scene(root, 600, 200);
        scene.setFill(Color.LIGHTGREEN);

        // Affecter la secne à la fenêtre.
        primaryStage.setScene(scene);

        // Titre de la fenêtre.
        primaryStage.setTitle("Animation d'un Rectangle avec AnimationTimer");

        // Empecher de modifier la taille.
        primaryStage.setResizable(false);

        // Afficher la fenêtre.
        primaryStage.show();
    }

    private HBox creerLabelNbFrame() {
        Label lblTitreFrame = new Label("NbFrames :");
        lblNbFrame = new Label("0");

        lblTitreFrame.setFont(Font.font(null, FontWeight.BOLD, 20));
        lblNbFrame.setFont(Font.font(null, FontWeight.BOLD, 20));

        HBox ligne = new HBox(5, lblTitreFrame, lblNbFrame);
        return ligne;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private AnimationTimer animerRectangle (Rectangle rect) {
        AnimationTimer anim = new AnimationTimer() {
            @Override
            public void handle(long now) {
                nbFrame++;
                lblNbFrame.setText(String.valueOf(nbFrame));
                if (rect.opacityProperty().get() > 0) {
                    rect.opacityProperty().set(rect.opacityProperty().get() - 0.01);
                } else {
                    stop();
                }

                //if (rect.xProperty().get() < 400) {
                //    rect.xProperty().set(rect.xProperty().get() + 1);
                //}
            }
        };

        return anim;
    }
}
