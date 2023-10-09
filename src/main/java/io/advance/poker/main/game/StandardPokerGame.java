package io.advance.poker.main.game;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import io.advance.poker.main.model.Card;
import io.advance.poker.main.model.Rank;
import io.advance.poker.main.model.Suit;

public class StandardPokerGame extends PokerGame {

    public StandardPokerGame(final Function<List<Card>, String> handEvaluator,
                             final int players, final int cardsPerPlayer,
                             final Optional<Random> shufflingMethod) {
        super(handEvaluator, players, cardsPerPlayer, shufflingMethod);
        this.shuffle();
    }

    @Override
    public List<Card> initializeDeck() {
        final List<Card> deck = new ArrayList<>();
        Arrays.stream(Suit.values()).forEach(suit -> deck.addAll(this.getCardsForSuit(suit)));
        return deck;
    }

    private List<Card> getCardsForSuit(final Suit suit) {
        final Set<Card> cards = new HashSet<>();
        IntStream.rangeClosed(0, 12).forEach(i -> Rank.getByWeight(i).ifPresent(rank -> cards.add(new Card(suit, rank))));
        return new ArrayList<>(cards);
    }

    @Override
    public String evaluate() {
        return super.evaluate();
    }
}
