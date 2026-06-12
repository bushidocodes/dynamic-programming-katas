package edu.gwu.csci6212;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoinPickingGameTest {

    @Test
    void rejectsOddNumberOfCoins() {
        assertThrows(IllegalArgumentException.class,
                () -> new CoinPickingGame(new int[] { 1, 1, 1 }));
    }

    @Test
    void rejectsZeroCoins() {
        assertThrows(IllegalArgumentException.class,
                () -> new CoinPickingGame(new int[] {}));
    }

    @Test
    void rejectsNegativeCoinValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new CoinPickingGame(new int[] { 1, -1, 1, 1 }));
    }

    @Test
    void acceptsEvenNumberOfCoins() {
        new CoinPickingGame(new int[] { 1, 1, 1, 1 });
    }

    @Test
    void playReturnsPositiveResult() {
        CoinPickingGame game = new CoinPickingGame(new int[] { 1, 1, 1, 1 });
        assertTrue(game.play() > 0);
    }

    @Test
    void optimizesWhenGreedyIsSufficient() {
        CoinPickingGame game = new CoinPickingGame(new int[] { 5, 3, 7, 10 });
        assertEquals(15, game.play());
    }

    @Test
    void optimizesWhenGreedyIsInsufficient() {
        CoinPickingGame game = new CoinPickingGame(new int[] { 8, 15, 3, 7 });
        assertEquals(22, game.play());
    }
}
