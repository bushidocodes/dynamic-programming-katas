package edu.gwu.csci6212;

import java.util.ArrayDeque;
import java.util.Deque;

public final class MinimumStepsChessKnight {
    private static final int[][] KNIGHT_MOVES = {
            { 1,  2 }, { 1, -2 }, { 2,  1 }, { 2, -1 },
            { -2, 1 }, { -2, -1 }, { -1,  2 }, { -1, -2 }
    };

    private record Position(int x, int y, int steps) {}

    private MinimumStepsChessKnight() {}

    public static int minSteps(int boardSize, int startX, int startY, int goalX, int goalY) {
        if (!inBounds(startX, startY, boardSize) || !inBounds(goalX, goalY, boardSize)) {
            return -1;
        }
        if (startX == goalX && startY == goalY) {
            return 0;
        }

        boolean[][] visited = new boolean[boardSize][boardSize];
        visited[startX][startY] = true;

        Deque<Position> queue = new ArrayDeque<>();
        queue.add(new Position(startX, startY, 0));

        while (!queue.isEmpty()) {
            Position curr = queue.remove();
            for (int[] move : KNIGHT_MOVES) {
                int nx = curr.x() + move[0];
                int ny = curr.y() + move[1];
                if (!inBounds(nx, ny, boardSize) || visited[nx][ny]) continue;
                int nextSteps = curr.steps() + 1;
                if (nx == goalX && ny == goalY) return nextSteps;
                visited[nx][ny] = true;
                queue.add(new Position(nx, ny, nextSteps));
            }
        }
        return -1;
    }

    private static boolean inBounds(int x, int y, int size) {
        return x >= 0 && y >= 0 && x < size && y < size;
    }
}
