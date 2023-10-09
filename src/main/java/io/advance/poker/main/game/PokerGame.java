package io.advance.poker.main.game;

import io.advance.poker.main.model.Card;

import java.util.*;
import java.util.function.Function;

//Standard Poker Game. Other Poker Games can extend and override methods
public abstract class PokerGame {

    private final Function<List<Card>, String> handEvaluator;

    private final int players;

    private final int cardsPerPlayer;

    private final Optional<Random> shufflingMethod;

    private List<Card> deck;

    public PokerGame(final Function<List<Card>, String> handEvaluator,
                     final int players,
                     final int cardsPerPlayer,
                     final Optional<Random> shufflingMethod) {
        this.handEvaluator = handEvaluator;
        this.players = players;
        this.cardsPerPlayer = cardsPerPlayer;
        this.shufflingMethod = shufflingMethod;
        this.deck = initializeDeck();
    }

    public String evaluate() {
        final StringBuilder evaluations = new StringBuilder();
        final Map<Integer, List<Card>> game = deal();
        game.forEach((playerId, cards) -> {
            System.out.println("Your hand: " + cards);
            final String evaluated = this.handEvaluator.apply(cards);
            System.out.println("You have: " + evaluated);
            evaluations.append(evaluated);
        });
        return evaluations.toString();
    }

    public Map<Integer, List<Card>> deal() {
        final Deque<Card> tmp = new ArrayDeque<>(deck);
        final Map<Integer, List<Card>> map = new HashMap<>();
        for (int i = 1; i <= players; i++) {
            final List<Card> playerCards = new ArrayList<>();
            for (int j = 0; j < cardsPerPlayer; j++) {
                playerCards.add(tmp.pollFirst());
            }
            map.put(i, playerCards);
        }
        return map;
    }

    public abstract List<Card> initializeDeck();

    public void shuffle() {
        System.out.println("Shuffling… Shuffling… Shuffling…");
        if (this.getShufflingMethod().isPresent()) {
            Collections.shuffle(deck, this.getShufflingMethod().get());
        } else {
            Collections.shuffle(deck);
        }
        System.out.println("Done Shuffling");
    }

    public Optional<Random> getShufflingMethod() {
        return this.shufflingMethod;
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public void setDeck(final List<Card> deck) {
        this.deck = deck;
    }
}
