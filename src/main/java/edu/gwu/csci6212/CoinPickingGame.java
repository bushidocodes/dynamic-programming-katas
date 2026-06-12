package edu.gwu.csci6212;

public final class CoinPickingGame {
    private record Scores(int playerOne, int playerTwo) {}

    private CoinPickingGame() {}

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
