package edu.gwu.csci6212;

public final class HouseRobbing {
    private HouseRobbing() {}

    /**
     * Returns the maximum loot that can be collected from houses arranged in a circle,
     * where no two adjacent houses may be robbed.
     *
     * @param houses non-null, non-empty array of non-negative house values
     * @return maximum loot collectable without triggering any alarm
     * @throws IllegalArgumentException if houses is null, empty, or contains a negative value
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

    /**
     * Solves the non-circular house-robber DP over houses[from..to] (inclusive).
     */
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
