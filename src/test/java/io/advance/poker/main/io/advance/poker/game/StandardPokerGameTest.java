package io.advance.poker.main.io.advance.poker.game;

import io.advance.poker.main.game.StandardPokerGame;
import io.advance.poker.main.logic.StandardPokerEvaluation;
import io.advance.poker.main.model.Card;
import io.advance.poker.main.model.Rank;
import io.advance.poker.main.model.StandardPokerHand;
import io.advance.poker.main.model.Suit;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StandardPokerGameTest {

    private final StandardPokerGame standardPokerGame = new StandardPokerGame(
            new StandardPokerEvaluation(),
            1,
            5,
            Optional.empty());

    @Test
    public void initializeDeck() {
        final List<Card> deck = standardPokerGame.getDeck();
        assertNotNull(deck);
        assertEquals(52, deck.size());
    }

    @Test
    public void testEvaluateThreeOfAKind() {
        final Card[] cards = new Card[]{
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.DIAMONDS, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.SPADES, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.KING),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.THREE_OF_A_KIND, output);

    }

    @Test
    public void testStraightFlush() {
        final Card[] cards = new Card[]{
                new Card(Suit.CLUBS, Rank.JACK),
                new Card(Suit.CLUBS, Rank.TEN),
                new Card(Suit.CLUBS, Rank.NINE),
                new Card(Suit.CLUBS, Rank.EIGHT),
                new Card(Suit.CLUBS, Rank.SEVEN),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.STRAIGHT_FLUSH, output);
    }

    @Test
    public void testFourOfAKind() {
        final Card[] cards = new Card[]{
                new Card(Suit.CLUBS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.SPADES, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.TWO),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.FOUR_OF_A_KIND, output);
    }

    @Test
    public void testFullHouse() {
        final Card[] cards = new Card[]{
                new Card(Suit.SPADES, Rank.SIX),
                new Card(Suit.HEARTS, Rank.SIX),
                new Card(Suit.DIAMONDS, Rank.SIX),
                new Card(Suit.CLUBS, Rank.KING),
                new Card(Suit.HEARTS, Rank.KING),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.FULL_HOUSE, output);
    }

    @Test
    public void testFlush() {
        final Card[] cards = new Card[]{
                new Card(Suit.DIAMONDS, Rank.JACK),
                new Card(Suit.DIAMONDS, Rank.NINE),
                new Card(Suit.DIAMONDS, Rank.EIGHT),
                new Card(Suit.DIAMONDS, Rank.FOUR),
                new Card(Suit.DIAMONDS, Rank.THREE),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.FLUSH, output);
    }

    @Test
    public void testStraight() {
        final Card[] cards = new Card[]{
                new Card(Suit.DIAMONDS, Rank.TEN),
                new Card(Suit.SPADES, Rank.NINE),
                new Card(Suit.HEARTS, Rank.EIGHT),
                new Card(Suit.DIAMONDS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.SIX),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.STRAIGHT, output);
    }

    @Test
    public void testThreeOfAKind() {
        final Card[] cards = new Card[]{
                new Card(Suit.CLUBS, Rank.QUEEN),
                new Card(Suit.SPADES, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.QUEEN),
                new Card(Suit.HEARTS, Rank.NINE),
                new Card(Suit.SPADES, Rank.TWO),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.THREE_OF_A_KIND, output);
    }

    @Test
    public void testTwoPair() {
        final Card[] cards = new Card[]{
                new Card(Suit.HEARTS, Rank.JACK),
                new Card(Suit.SPADES, Rank.JACK),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.SPADES, Rank.THREE),
                new Card(Suit.HEARTS, Rank.TWO),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.TWO_PAIR, output);
    }

    @Test
    public void testOnePair() {
        final Card[] cards = new Card[]{
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.SPADES, Rank.EIGHT),
                new Card(Suit.HEARTS, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.FOUR),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.ONE_PAIR, output);
    }

    @Test
    public void testHighCards() {
        final Card[] cards = new Card[]{
                new Card(Suit.DIAMONDS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.QUEEN),
                new Card(Suit.SPADES, Rank.SEVEN),
                new Card(Suit.SPADES, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.THREE),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        final String output = standardPokerGame.evaluate();

        assertEquals(StandardPokerHand.HIGH_CARDS, output);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateIllegalHand() {
        final Card[] cards = new Card[]{
                new Card(Suit.HEARTS, Rank.KING),
                new Card(Suit.HEARTS, Rank.KING),
                new Card(Suit.DIAMONDS, Rank.JACK),
                new Card(Suit.SPADES, Rank.TEN),
                new Card(Suit.HEARTS, Rank.ACE),
        };

        this.standardPokerGame.setDeck(Arrays.asList(cards));

        this.standardPokerGame.evaluate();
    }
}
