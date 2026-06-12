package edu.gwu.csci6212;

/**
 * Solves the coin-picking game using dynamic programming.
 *
 * <p>Given a row of {@code n} coins (n even), two players alternate turns. On
 * each turn the active player takes either the leftmost or the rightmost
 * remaining coin. Both players play optimally. This class computes the maximum
 * score the first player can guarantee.
 *
 * <p>The DP state is a 2-D memo table where {@code memo[right][left]} stores
 * the (playerOne score, playerTwo score) for the sub-row {@code coins[left..right]}.
 * Sub-rows of length 1 are base cases; longer sub-rows are built from shorter ones
 * by choosing whichever end maximises the current player's total.
 */
public final class CoinPickingGame {
    private record Scores(int playerOne, int playerTwo) {}

    private CoinPickingGame() {}

    /**
     * Returns the maximum score the first player can guarantee.
     *
     * @param coins non-null array of non-negative coin values; length must be a
     *              positive even number
     * @return maximum score achievable by the first player under optimal play
     * @throws IllegalArgumentException if {@code coins} is empty, has odd length,
     *                                  or contains a negative value
     */
    public static int maxScore(int[] coins) {
        if (coins.length == 0 || coins.length % 2 != 0) {
            throw new IllegalArgumentException(
                    "coin count must be a positive even number, was " + coins.length);
        }
        for (int coin : coins) {
            if (coin < 0) {
                throw new IllegalArgumentException(
                        "all coin values must be non-negative, found " + coin);
            }
        }
        Scores[][] memo = new Scores[coins.length][coins.length];
        fillMemo(coins, memo);
        return memo[coins.length - 1][0].playerOne();
    }

    private static void fillMemo(int[] coins, Scores[][] memo) {
        for (int i = 0; i < coins.length; i++) {
            memo[i][i] = new Scores(coins[i], 0);
        }
        for (int span = 1; span < coins.length; span++) {
            for (int right = span; right < coins.length; right++) {
                int left = right - span;
                memo[right][left] = bestChoice(coins, memo, right, left);
            }
        }
    }

    private static Scores bestChoice(int[] coins, Scores[][] memo, int right, int left) {
        Scores takeLeft = new Scores(
                memo[right][left + 1].playerTwo() + coins[left],
                memo[right][left + 1].playerOne());
        Scores takeRight = new Scores(
                memo[right - 1][left].playerTwo() + coins[right],
                memo[right - 1][left].playerOne());
        return takeLeft.playerOne() >= takeRight.playerOne() ? takeLeft : takeRight;
    }
}
