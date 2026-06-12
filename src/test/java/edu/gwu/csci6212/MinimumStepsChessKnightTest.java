package edu.gwu.csci6212;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MinimumStepsChessKnightTest {

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
}
