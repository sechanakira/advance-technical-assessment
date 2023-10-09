package io.advance.poker.main.model;

import io.advance.poker.main.logic.Tables;

public class Card {

    private Suit suit;
    private Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(final Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        int value =
                (1 << (this.getRank().getWeight() + 16)) | this.getSuit().getWeight() | (this.getRank().getWeight() << 8)
                        | Tables.PRIMES[this.getRank().getWeight()];
        return value;
    }

    @Override
    public String toString() {
        return this.rank.getPrinterFriendlyName() + " " + this.suit.getSymbol();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Card card = (Card) o;

        if (suit != card.suit) return false;
        return rank == card.rank;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }
}
