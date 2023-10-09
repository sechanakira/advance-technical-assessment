package io.advance.poker.main.model;

import java.util.Arrays;
import java.util.Optional;

public enum Rank {

    TWO(0, "2"),
    THREE(1, "3"),
    FOUR(2, "4"),
    FIVE(3, "5"),
    SIX(4, "6"),
    SEVEN(5, "7"),
    EIGHT(6, "8"),
    NINE(7, "9"),
    TEN(8, "10"),
    JACK(9, "J"),
    QUEEN(10, "Q"),
    KING(11, "K"),
    ACE(12, "A");

    private final int weight;
    private final String printerFriendlyName;

    Rank(final int weight, final String printerFriendlyName) {
        this.weight = weight;
        this.printerFriendlyName = printerFriendlyName;
    }

    public static Optional<Rank> getByWeight(int weight) {
        return Arrays.stream(Rank.values()).filter(rank -> rank.weight == weight).findAny();
    }

    public int getWeight() {
        return weight;
    }

    public String getPrinterFriendlyName() {
        return this.printerFriendlyName;
    }
}
