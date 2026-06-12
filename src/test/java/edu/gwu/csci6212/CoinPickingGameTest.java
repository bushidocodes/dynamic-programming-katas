package edu.gwu.csci6212;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CoinPickingGameTest {

    @Test
    void rejectsOddNumberOfCoins() {
        assertThrows(IllegalArgumentException.class,
                () -> CoinPickingGame.maxScore(new int[] { 1, 1, 1 }));
    }

    @Test
    void rejectsZeroCoins() {
        assertThrows(IllegalArgumentException.class,
                () -> CoinPickingGame.maxScore(new int[] {}));
    }

    @Test
    void rejectsNegativeCoinValue() {
        assertThrows(IllegalArgumentException.class,
                () -> CoinPickingGame.maxScore(new int[] { 1, -1, 1, 1 }));
    }

    @Test
    void twoEqualCoinsReturnsEitherValue() {
        assertEquals(1, CoinPickingGame.maxScore(new int[] { 1, 1 }));
    }

    @Test
    void optimizesWhenGreedyIsSufficient() {
        assertEquals(15, CoinPickingGame.maxScore(new int[] { 5, 3, 7, 10 }));
    }

    @Test
    void optimizesWhenGreedyIsInsufficient() {
        assertEquals(22, CoinPickingGame.maxScore(new int[] { 8, 15, 3, 7 }));
    }
}
