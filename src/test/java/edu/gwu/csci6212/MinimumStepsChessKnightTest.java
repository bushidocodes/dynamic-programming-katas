package edu.gwu.csci6212;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MinimumStepsChessKnightTest {

    @Test
    void rejectsZeroBoardSize() {
        assertThrows(IllegalArgumentException.class,
                () -> MinimumStepsChessKnight.minSteps(0, 0, 0, 0, 0));
    }

    @Test
    void rejectsNegativeBoardSize() {
        assertThrows(IllegalArgumentException.class,
                () -> MinimumStepsChessKnight.minSteps(-1, 0, 0, 0, 0));
    }

    @Test
    void returnsMinusOneWhenStartOutOfBounds() {
        assertEquals(-1, MinimumStepsChessKnight.minSteps(7, 7, 5, 1, 1));
    }

    @Test
    void returnsZeroWhenStartEqualsGoal() {
        assertEquals(0, MinimumStepsChessKnight.minSteps(7, 1, 1, 1, 1));
    }

    @Test
    void findsTwoStepPath() {
        assertEquals(2, MinimumStepsChessKnight.minSteps(7, 3, 1, 1, 1));
    }

    @Test
    void findsThreeStepPath() {
        assertEquals(3, MinimumStepsChessKnight.minSteps(7, 4, 5, 1, 1));
    }

    @Test
    void findsFourStepPath() {
        assertEquals(4, MinimumStepsChessKnight.minSteps(7, 1, 1, 6, 6));
    }

    @Test
    void findsFourStepPathFromBoardCorner() {
        assertEquals(4, MinimumStepsChessKnight.minSteps(8, 0, 0, 1, 1));
    }

    @Test
    void returnsMinusOneWhenGoalOutOfBounds() {
        assertEquals(-1, MinimumStepsChessKnight.minSteps(7, 1, 1, 7, 5));
    }

    @Test
    void findsOneStepPath() {
        // (0,0) -> (1,2) is a single knight move
        assertEquals(1, MinimumStepsChessKnight.minSteps(7, 0, 0, 1, 2));
    }

    @Test
    void returnsMinusOneWhenGoalUnreachable() {
        // On a 2x2 board every knight move leaves the board, so no cell other than
        // the start is reachable; the dist table entry stays -1.
        assertEquals(-1, MinimumStepsChessKnight.minSteps(2, 0, 0, 1, 1));
    }

    @Test
    void pathLengthIsSymmetric() {
        // The minimum steps from A to B must equal the minimum steps from B to A.
        int forward = MinimumStepsChessKnight.minSteps(7, 4, 5, 1, 1);
        int reverse = MinimumStepsChessKnight.minSteps(7, 1, 1, 4, 5);
        assertEquals(forward, reverse);
    }
}
