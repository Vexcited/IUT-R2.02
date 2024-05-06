package unilim.info.ihm.tp3.model;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class AchatCondiment {
    private final String nom;
    private final Color couleur;
    private final ImageView image;
    private final String prix;
    private final String quantite;

    public AchatCondiment(String nom, Color couleur, ImageView image, String price, String quantity) {
        this.nom = nom;
        this.couleur = couleur;
        this.image = image;
        this.prix = price;
        this.quantite = quantity;
    }

    public Color getCouleur() {
        return couleur;
    }

    public ImageView getImage() {
        return image;
    }

    public String getPrix() {
        return prix;
    }

    public String getQuantite() {
        return quantite;
    }

    public String getNom() {
        return nom;
    }
}
