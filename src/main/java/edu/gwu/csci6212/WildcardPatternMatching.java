package edu.gwu.csci6212;

public final class WildcardPatternMatching {
    private WildcardPatternMatching() {}

    /**
     * Returns true if {@code pattern} matches the entire {@code text}.
     *
     * <p>The pattern may contain:
     * <ul>
     *   <li>{@code *} – matches any sequence of characters (including empty)</li>
     *   <li>{@code ?} – matches exactly one character</li>
     *   <li>any other character – must match that character exactly</li>
     * </ul>
     *
     * @param text    the input string to test (must not be null)
     * @param pattern the wildcard pattern (must not be null)
     * @return true if the pattern matches the entire text
     * @throws IllegalArgumentException if text or pattern is null
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
