package edu.gwu.csci6212;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Finds the minimum number of knight moves between two squares on an N×N
 * chessboard using dynamic programming.
 *
 * <p>A 2-D distance table {@code dist[x][y]} is initialised to {@code -1}
 * (unvisited). Starting from the source square ({@code dist = 0}), the table
 * is filled in wavefront order: each reachable square records
 * {@code dist[nx][ny] = dist[cx][cy] + 1}. The queue only drives fill order;
 * the table itself is the DP state. The answer is {@code dist[goalX][goalY]},
 * or {@code -1} if the goal is unreachable.
 */
public final class MinimumStepsChessKnight {
    private static final int[][] KNIGHT_MOVES = {
            { 1,  2 }, { 1, -2 }, { 2,  1 }, { 2, -1 },
            { -2, 1 }, { -2, -1 }, { -1,  2 }, { -1, -2 }
    };

    private MinimumStepsChessKnight() {}

    /**
     * Returns the minimum number of knight moves to travel from
     * {@code (startX, startY)} to {@code (goalX, goalY)} on a
     * {@code boardSize × boardSize} chessboard.
     *
     * @param boardSize size of the square board; must be a positive integer
     * @param startX    row of the starting square (0-based)
     * @param startY    column of the starting square (0-based)
     * @param goalX     row of the target square (0-based)
     * @param goalY     column of the target square (0-based)
     * @return minimum number of moves, {@code 0} if start equals goal, or
     *         {@code -1} if the start or goal is out of bounds or the goal is
     *         unreachable
     * @throws IllegalArgumentException if {@code boardSize} is not positive
     */
    public static int minSteps(int boardSize, int startX, int startY, int goalX, int goalY) {
        if (boardSize <= 0) {
            throw new IllegalArgumentException(
                    "boardSize must be a positive integer, was " + boardSize);
        }
        if (!inBounds(startX, startY, boardSize) || !inBounds(goalX, goalY, boardSize)) {
            return -1;
        }

        int[][] dist = new int[boardSize][boardSize];
        for (int[] row : dist) Arrays.fill(row, -1);
        dist[startX][startY] = 0;

        // Queue drives fill order; dist[][] is the DP state.
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();
            int cx = curr[0], cy = curr[1];
            for (int[] move : KNIGHT_MOVES) {
                int nx = cx + move[0];
                int ny = cy + move[1];
                if (!inBounds(nx, ny, boardSize) || dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[cx][cy] + 1;
                queue.add(new int[]{nx, ny});
            }
        }

        return dist[goalX][goalY];
    }

    private static boolean inBounds(int x, int y, int size) {
        return x >= 0 && y >= 0 && x < size && y < size;
    }
}
