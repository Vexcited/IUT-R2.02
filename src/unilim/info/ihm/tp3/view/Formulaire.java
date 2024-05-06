package unilim.info.ihm.tp3.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Formulaire extends VBox {
    private final Label nomError = new Label("Veuillez saisir le nom");
    private final Label typeError = new Label("Veuillez saisir le type");
    private final Label priceError = new Label("Veuillez saisir le prix");
    private final Label quantityError = new Label("Veuillez saisir la quantité");
    private final TextField nomField = new TextField();
    private final TextField typeField = new TextField();
    private final TextField priceField = new TextField();
    private final TextField quantityField = new TextField();
    private final Label imagePath = new Label();
    private final Button photoButton = new Button("Photo");
    private final Button saveButton = new Button("Sauvegarder");

    public Formulaire() {
        Label nom = new Label("Nom * :");
        nomError.setVisible(false);

        Label type = new Label("Type * :");
        typeError.setVisible(false);

        Label price = new Label("Prix * :");
        priceError.setVisible(false);

        Label quantity = new Label("Quantité * :");
        quantityError.setVisible(false);

        Label image = new Label("Image * :");

        getChildren().addAll(
                nom, nomField, nomError,
                type, typeField, typeError,
                price, priceField, priceError,
                quantity, quantityField, quantityError,
                image, imagePath,

                photoButton,
                saveButton
        );
    }

    public Label getNomError() {
        return nomError;
    }

    public Label getTypeError() {
        return typeError;
    }

    public Label getPriceError() {
        return priceError;
    }

    public Label getQuantityError() {
        return quantityError;
    }

    public TextField getNomField() {
        return nomField;
    }

    public TextField getTypeField() {
        return typeField;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public TextField getQuantityField() {
        return quantityField;
    }

    public Label getImagePath() {
        return imagePath;
    }

    public Button getPhotoButton() {
        return photoButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
