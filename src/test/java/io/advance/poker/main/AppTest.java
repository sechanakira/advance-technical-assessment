package io.advance.poker.main;

import org.junit.Test;

import io.advance.poker.main.game.StandardPokerGame;
import io.advance.poker.main.logic.StandardPokerEvaluation;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

public class AppTest {

    @Test
    public void applicationTest() {
        final StandardPokerGame pokerGame = new StandardPokerGame(
                new StandardPokerEvaluation(),
                1,
                5,
                Optional.empty());
        final String output = pokerGame.evaluate();

        assertNotNull(output);
    }
}
