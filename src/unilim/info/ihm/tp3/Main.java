package unilim.info.ihm.tp3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import unilim.info.ihm.tp3.controller.ChoixPhotoController;
import unilim.info.ihm.tp3.controller.SauvegardeController;
import unilim.info.ihm.tp3.view.Formulaire;
import unilim.info.ihm.tp3.view.ListeCondiments;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        root.setPadding(new Insets(16));

        Formulaire form = new Formulaire();
        GridPane.setVgrow(form, Priority.ALWAYS);
        form.setMinWidth(300);

        ListeCondiments condiments = new ListeCondiments();
        GridPane.setVgrow(condiments, Priority.ALWAYS);
        GridPane.setHgrow(condiments, Priority.ALWAYS);

        form.getSaveButton().setOnMouseClicked(new SauvegardeController(form, condiments));
        form.getPhotoButton().setOnMouseClicked(new ChoixPhotoController(form.getImagePath(), primaryStage));

        Label saveLbl = new Label("Sauvegarde de condiment");
        saveLbl.setMaxWidth(Double.MAX_VALUE);
        saveLbl.setAlignment(Pos.CENTER);

        root.add(saveLbl, 0, 0);
        root.add(form, 0, 1);

        Label listLbl = new Label("Liste des condiments");
        listLbl.setMaxWidth(Double.MAX_VALUE);
        listLbl.setAlignment(Pos.CENTER);

        root.add(listLbl, 1, 0);
        root.add(condiments, 1, 1);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Outils d'achat de condiments");
        primaryStage.show();
    }
}
