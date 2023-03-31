package Entities;

public class Cards {
    private final String suit;
    private final String rank;
    private final String type;

    public Cards(String suit, String rank, String type) {
        this.suit = suit;
        this.rank = rank;
        this.type = type;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return rank + "_of_" + suit;
    }
}
