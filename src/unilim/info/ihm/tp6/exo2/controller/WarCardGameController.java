package unilim.info.ihm.tp6.exo2.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import unilim.info.ihm.tp6.exo2.model.Card;
import unilim.info.ihm.tp6.exo2.tools.CardGameTools;
import unilim.info.ihm.tp6.exo2.view.CardImageView;

import java.util.ArrayList;

public class WarCardGameController {

    @FXML
    private Button idBtnChanger;

    @FXML
    private Button idBtnQuitter;

    @FXML
    private Pane idCardToBeat;

    @FXML
    private HBox idDeck;

    private void clearAndGenerateDeck () {
        // We clear the current deck.
        idDeck.getChildren().clear();

        // We generate five cards.
        for (int i = 0; i < 5; i++) {
            Card card = new Card(CardGameTools.generateCardValue());
            CardImageView imageView = new CardImageView(card);

            idDeck.getChildren().add(imageView);

            // We add support for drag on the cards in the deck.
            DndCardController.manageSourceDnD(imageView, idDeck);

        }
    }

    @FXML
    void initialize() {
        // We generate the enemy card.
        Card enemyCard = new Card(CardGameTools.generateCardValue());
        CardImageView imageView = new CardImageView(enemyCard);
        imageView.setLayoutX(325); // 325 = center of the screen.
        idCardToBeat.getChildren().add(imageView);

        // We add support for drop on the card to beat.
        DndCardController.manageTargetDnD(imageView, idCardToBeat);

        // We generate the deck.
        clearAndGenerateDeck();
    }

    @FXML
    void changer(ActionEvent event) {
        clearAndGenerateDeck();
    }

    @FXML
    void quitter(ActionEvent event) {
        Platform.exit();
    }
}
