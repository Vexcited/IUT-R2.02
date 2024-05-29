package unilim.info.ihm.tp6.exo2.tools;

import javafx.scene.image.Image;
import unilim.info.ihm.tp5.exo3.ImageLoader;
import unilim.info.ihm.tp6.exo2.model.Card;

import java.util.Objects;
import java.util.Random;

public class CardGameTools {
    private static final Random random = new Random();

    /**
     * Generate a random value between 1 and 12 (included)
     */
    public static int generateCardValue() {
        return random.nextInt(12) + 1;
    }

    public static Image loadCardImage(Card card) {
        String fileName = "../images/" + card.getValue() + ".png";
        String path = Objects.requireNonNull(CardGameTools.class.getResource(fileName)).toExternalForm();

        return new Image(path);
    }
}
