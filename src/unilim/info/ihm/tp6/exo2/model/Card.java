package unilim.info.ihm.tp6.exo2.model;

public class Card {
    private final Integer value;

    public Card (Integer value) {
        if (value > 12 || value < 1) throw new IllegalArgumentException("Value must be between 1 and 12");
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
