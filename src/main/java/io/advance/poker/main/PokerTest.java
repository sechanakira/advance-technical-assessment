package io.advance.poker.main;

import io.advance.poker.main.game.StandardPokerGame;
import io.advance.poker.main.logic.StandardPokerEvaluation;

import java.util.Optional;

public class PokerTest {
    public static void main(String[] args) {
        final StandardPokerGame pokerGame = new StandardPokerGame(
                new StandardPokerEvaluation(),
                1,
                5,
                Optional.empty());
        pokerGame.evaluate();
    }
}
