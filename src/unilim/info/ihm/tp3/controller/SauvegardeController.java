package unilim.info.ihm.tp3.controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import unilim.info.ihm.tp3.model.AchatCondiment;
import unilim.info.ihm.tp3.view.Formulaire;
import unilim.info.ihm.tp3.view.ListeCondiments;

public class SauvegardeController implements EventHandler<MouseEvent> {
    private final Formulaire form;
    private  final ListeCondiments condiments;

    public SauvegardeController(Formulaire form, ListeCondiments condiments) {
        this.form = form;
        this.condiments = condiments;
    }

    @Override
    public void handle(MouseEvent event) {
        String nom = form.getNomField().getText().trim();
        String type = form.getTypeField().getText().trim().toLowerCase();
        String price = form.getPriceField().getText().trim();
        String quantity = form.getQuantityField().getText().trim();
        String imagePath = form.getImagePath().getText();

        boolean nomFilled = !nom.isEmpty();
        boolean typeFilled = !type.isEmpty();
        boolean priceFilled = !price.isEmpty();
        boolean quantityFilled = !quantity.isEmpty();
        boolean imageFilled = !imagePath.isEmpty();

        form.getNomError().setVisible(!nomFilled);
        form.getTypeError().setVisible(!typeFilled);
        form.getPriceError().setVisible(!priceFilled);
        form.getQuantityError().setVisible(!quantityFilled);

        Color color;
        if (type.equals("fruit")) {
            color = Color.RED;
        }
        else if (type.equals("légume")) {
            color = Color.GREEN;
        }
        else {
            form.getTypeError().setVisible(true);
            return;
        }

        if (!nomFilled || !priceFilled || !quantityFilled || !imageFilled) return;

        Image image = new Image(imagePath, 140, 140, true, false);
        ImageView imageView = new ImageView(image);

        AchatCondiment condiment = new AchatCondiment(nom, color, imageView, price, quantity);
        condiments.addCondiment(condiment);
    }
}
