package io.advance.poker.main.logic;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import io.advance.poker.main.model.Card;
import io.advance.poker.main.model.StandardPokerHand;

//Standard Poker Hand Evaluation
public class StandardPokerEvaluation implements Function<List<Card>, String> {

	private static int evaluate(List<Card> cards) {
		// Only 5-card hands are supported
		if (cards == null || cards.size() != 5) {
			throw new IllegalArgumentException("Exactly 5 cards are required.");
		}

		// Binary representations of each card
		final int c1 = cards.get(0).getValue();
		final int c2 = cards.get(1).getValue();
		final int c3 = cards.get(2).getValue();
		final int c4 = cards.get(3).getValue();
		final int c5 = cards.get(4).getValue();

		// No duplicate cards allowed
		if (hasDuplicates(new int[]{c1, c2, c3, c4, c5})) {
			throw new IllegalArgumentException("Illegal hand.");
		}

		// Calculate index in the flushes/unique table
		final int index = (c1 | c2 | c3 | c4 | c5) >> 16;

		// Flushes, including straight flushes
		if ((c1 & c2 & c3 & c4 & c5 & 0xF000) != 0) {
			return Tables.Flushes.TABLE[index];
		}

		// Straight and high card hands
		final int value = Tables.Unique.TABLE[index];
		if (value != 0) {
			return value;
		}

		// Remaining cards
		final int product = (c1 & 0xFF) * (c2 & 0xFF) * (c3 & 0xFF) * (c4 & 0xFF) * (c5 & 0xFF);
		return Tables.Hash.Values.TABLE[hash(product)];
	}

	private static boolean hasDuplicates(int[] values) {
		Arrays.sort(values);
		for (int i = 1; i < values.length; i++) {
			if (values[i] == values[i - 1]) {
				return true;
			}
		}
		return false;
	}

	private static int hash(int key) {
		key += 0xE91AAA35;
		key ^= key >>> 16;
		key += key << 8;
		key ^= key >>> 4;
		return ((key + (key << 2)) >>> 19) ^ Tables.Hash.Adjust.TABLE[(key >>> 8) & 0x1FF];
	}

	@Override
	public String apply(final List<Card> cards) {
		int handRank = evaluate(cards);
		if (handRank > 6185) {
			return StandardPokerHand.HIGH_CARDS;
		}
		if (handRank > 3325) {
			return StandardPokerHand.ONE_PAIR;
		}
		if (handRank > 2467) {
			return StandardPokerHand.TWO_PAIR;
		}
		if (handRank > 1609) {
			return StandardPokerHand.THREE_OF_A_KIND;
		}
		if (handRank > 1599) {
			return StandardPokerHand.STRAIGHT;
		}
		if (handRank > 322) {
			return StandardPokerHand.FLUSH;
		}
		if (handRank > 166) {
			return StandardPokerHand.FULL_HOUSE;
		}
		if (handRank > 10) {
			return StandardPokerHand.FOUR_OF_A_KIND;
		}
		return StandardPokerHand.STRAIGHT_FLUSH;
	}
}
