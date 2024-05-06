package unilim.info.ihm.tp3.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import unilim.info.ihm.tp3.model.AchatCondiment;

public class ListeCondiments extends VBox {
    public ListeCondiments() {
        setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
        setPadding(new Insets(16, 0, 0, 16));
        setSpacing(14);
    }

    public void addCondiment(AchatCondiment condiment) {
        if (getChildren().size() >= 4) return;

        HBox box = new HBox();
        VBox info = new VBox();

        Label nom = new Label("Nom : " + condiment.getNom());
        nom.setTextFill(condiment.getCouleur());
        Label prix = new Label("Prix : " + condiment.getPrix());
        Label quantite = new Label("Quantité : " + condiment.getQuantite());

        info.getChildren().addAll(nom, prix, quantite);
        box.getChildren().addAll(info, condiment.getImage());

        getChildren().add(box);
    }
}
