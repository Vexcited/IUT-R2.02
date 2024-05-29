package unilim.info.ihm.tp6.exo2.controller;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import unilim.info.ihm.tp6.exo2.model.Card;
import unilim.info.ihm.tp6.exo2.view.CardImageView;

public class DndCardController {
    public static void manageSourceDnD(CardImageView source, HBox deck) {
        source.setOnDragDetected(event -> {
            Dragboard drag = source.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent clipboard = new ClipboardContent();
            clipboard.putString(source.getCard().getValue().toString());
            drag.setContent(clipboard);
            event.consume();
        });

        source.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                deck.getChildren().remove(source);
            }

            event.consume();
        });
    }

    public static void manageTargetDnD(CardImageView target, Pane container) {
        target.setOnDragOver(event -> {
            if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        target.setOnDragDropped(event -> {
            boolean success = false;

            if (event.getDragboard().hasString()) {
                Integer sourceCardValue = Integer.parseInt(event.getDragboard().getString());

                if (sourceCardValue > target.getCard().getValue()) {
                    Card sourceCard = new Card(sourceCardValue);

                    CardImageView imageView = new CardImageView(sourceCard);
                    imageView.setLayoutX(325); // 325 = center of the screen.
                    // We replace the existing card (at index 0) with the new card.
                    container.getChildren().set(0, imageView);

                    // We add support for drop on the card to beat.
                    DndCardController.manageTargetDnD(imageView, container);

                    success = true;
                }
            }

            if (success) System.out.println("You played a card.");
            else System.out.println("You can't play this card.");

            event.setDropCompleted(success);
            event.consume();
        });
    }
}
