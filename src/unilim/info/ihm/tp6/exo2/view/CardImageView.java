package unilim.info.ihm.tp6.exo2.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unilim.info.ihm.tp6.exo2.model.Card;
import unilim.info.ihm.tp6.exo2.tools.CardGameTools;

public class CardImageView extends ImageView {
    private final Card card;

    public CardImageView(Card card) {
        super();
        if (card == null) throw new IllegalArgumentException("Card must not be null");

        Image image = CardGameTools.loadCardImage(card);
        this.setImage(image);

        this.card = card;
    }

    public Card getCard () {
        return card;
    }

}
