package edu.gwu.csci6212;

/**
 * Solves the circular house-robbing problem using dynamic programming.
 *
 * <p>Houses are arranged in a circle. Each house has a non-negative cash value.
 * Adjacent houses share an alarm: robbing two neighbours on the same night
 * triggers it. Because the layout is circular, the first and last houses are
 * also adjacent. The goal is to maximise total loot without triggering any alarm.
 *
 * <p>The circular constraint is handled by reducing the problem to two
 * independent linear subproblems — one excluding the last house and one
 * excluding the first — and returning the better result. Each linear
 * subproblem is solved with the standard two-variable DP recurrence
 * {@code dp[i] = max(dp[i-1], dp[i-2] + houses[i])}.
 */
public final class HouseRobbing {
    private HouseRobbing() {}

    /**
     * Returns the maximum loot collectable without robbing two adjacent houses.
     *
     * @param houses non-null, non-empty array of non-negative house values
     * @return maximum loot the robber can collect
     * @throws IllegalArgumentException if {@code houses} is null, empty, or
     *                                  contains a negative value
     */
    public static int maxLoot(int[] houses) {
        if (houses == null || houses.length == 0) {
            throw new IllegalArgumentException(
                    "houses must be a non-null, non-empty array");
        }
        for (int value : houses) {
            if (value < 0) {
                throw new IllegalArgumentException(
                        "all house values must be non-negative, found " + value);
            }
        }

        if (houses.length == 1) {
            return houses[0];
        }

        // Because house[0] and house[n-1] are adjacent (circular), split into two
        // linear subproblems and return the better result.
        return Math.max(
                linearMaxLoot(houses, 0, houses.length - 2),
                linearMaxLoot(houses, 1, houses.length - 1));
    }

    private static int linearMaxLoot(int[] houses, int from, int to) {
        if (from == to) {
            return houses[from];
        }

        int prev2 = houses[from];
        int prev1 = Math.max(houses[from], houses[from + 1]);

        for (int i = from + 2; i <= to; i++) {
            int curr = Math.max(prev1, prev2 + houses[i]);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
