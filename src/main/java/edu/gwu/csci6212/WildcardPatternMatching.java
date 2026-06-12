package edu.gwu.csci6212;

/**
 * Wildcard pattern matching using dynamic programming.
 *
 * <p>A pattern may contain two special characters:
 * <ul>
 *   <li>{@code *} — matches any sequence of characters, including the empty sequence</li>
 *   <li>{@code ?} — matches exactly one arbitrary character</li>
 * </ul>
 * All other characters must match literally. The pattern must match the
 * <em>entire</em> input string (not just a substring).
 *
 * <p>The DP state is a 2-D boolean table where {@code dp[i][j]} is {@code true}
 * iff {@code pattern[0..i-1]} matches {@code text[0..j-1]}. The recurrence is:
 * <ul>
 *   <li>{@code '*'}: {@code dp[i][j] = dp[i-1][j] || dp[i][j-1]}
 *       (star matches empty or one more character)</li>
 *   <li>{@code '?'} or exact match: {@code dp[i][j] = dp[i-1][j-1]}</li>
 * </ul>
 */
public final class WildcardPatternMatching {
    private WildcardPatternMatching() {}

    /**
     * Returns {@code true} if {@code pattern} matches the entire {@code text}.
     *
     * @param text    the input string to test; must not be null
     * @param pattern the wildcard pattern; must not be null
     * @return {@code true} if the pattern matches the entire text
     * @throws IllegalArgumentException if {@code text} or {@code pattern} is null
     */
    public static boolean matches(String text, String pattern) {
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("pattern must not be null");
        }

        int m = pattern.length();
        int n = text.length();

        // dp[i][j] = true iff pattern[0..i-1] matches text[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty pattern matches empty text
        dp[0][0] = true;

        // A prefix of the pattern consisting entirely of '*' matches empty text
        for (int i = 1; i <= m; i++) {
            if (pattern.charAt(i - 1) == '*') {
                dp[i][0] = dp[i - 1][0];
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            char p = pattern.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                if (p == '*') {
                    // dp[i-1][j]: '*' matches empty (skip the '*')
                    // dp[i][j-1]: '*' absorbs one more character from text
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (p == '?' || p == text.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // else dp[i][j] remains false
            }
        }

        return dp[m][n];
    }
}
