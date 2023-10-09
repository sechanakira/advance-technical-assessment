package io.advance.poker.main.model;

public enum Suit {

    HEARTS(0x2000, "♡"),
    DIAMONDS(0x4000, "♢"),
    SPADES(0x1000, "♠"),
    CLUBS(0x8000, "♣");

    private final int weight;
    private final String symbol;

    Suit(final int value, final String symbol) {
        this.weight = value;
        this.symbol = symbol;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getSymbol() {
        return this.symbol;
    }

}
