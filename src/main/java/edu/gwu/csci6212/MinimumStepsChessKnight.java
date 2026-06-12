package edu.gwu.csci6212;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public final class MinimumStepsChessKnight {
    private static final int[][] KNIGHT_MOVES = {
            { 1,  2 }, { 1, -2 }, { 2,  1 }, { 2, -1 },
            { -2, 1 }, { -2, -1 }, { -1,  2 }, { -1, -2 }
    };

    private MinimumStepsChessKnight() {}

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
