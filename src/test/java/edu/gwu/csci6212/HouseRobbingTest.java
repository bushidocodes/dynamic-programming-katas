package edu.gwu.csci6212;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class HouseRobbingTest {

    @Test
    void rejectsNullArray() {
        assertThrows(IllegalArgumentException.class,
                () -> HouseRobbing.maxLoot(null));
    }

    @Test
    void rejectsEmptyArray() {
        assertThrows(IllegalArgumentException.class,
                () -> HouseRobbing.maxLoot(new int[]{}));
    }

    @Test
    void rejectsNegativeHouseValue() {
        assertThrows(IllegalArgumentException.class,
                () -> HouseRobbing.maxLoot(new int[]{3, -1, 5}));
    }

    @Test
    void singleHouseReturnsThatValue() {
        assertEquals(42, HouseRobbing.maxLoot(new int[]{42}));
    }

    @Test
    void twoHousesReturnsTheLarger() {
        assertEquals(7, HouseRobbing.maxLoot(new int[]{3, 7}));
    }

    @Test
    void twoIdenticalHousesReturnsThatValue() {
        assertEquals(5, HouseRobbing.maxLoot(new int[]{5, 5}));
    }

    // Assignment example 1: [2, 3, 2] -> 3
    // Robbing house 1 (value 3) is the best; houses 0 and 2 are adjacent across the circle.
    @Test
    void assignmentExampleThreeHouses() {
        assertEquals(3, HouseRobbing.maxLoot(new int[]{2, 3, 2}));
    }

    // Assignment example 2: [1, 2, 3, 1] -> 4
    // Rob houses 0 and 2: 1 + 3 = 4.
    @Test
    void assignmentExampleFourHouses() {
        assertEquals(4, HouseRobbing.maxLoot(new int[]{1, 2, 3, 1}));
    }

    // Greedy "always take the largest adjacent end" would pick 9 first, then be blocked,
    // and end up with 9. The DP correctly picks 5 + 4 + 1 = 10 instead.
    // Array: [1, 5, 4, 2, 9] (circular)
    //   Subproblem 1 [1,5,4,2]: dp -> max(1+4, 5+2) = max(5,7) = 7
    //   Subproblem 2 [5,4,2,9]: dp -> max(5+2, 4+9) = max(7,13) = 13
    //   Answer: 13 (rob houses at indices 1,3,4 => 5+2+9 is invalid; correctly: indices 1 and 4 => 5+9 blocked; indices 0,2 unavail in sub2; let's verify)
    // Actually for [5,4,2,9]: best is rob index3=9 and index0=5 => 14? No, sub2 is houses[1..4]=[5,4,2,9]
    //   i=0: prev2=5, prev1=max(5,4)=5
    //   i=2 (value 2): curr=max(5, 5+2)=7, prev2=5, prev1=7
    //   i=3 (value 9): curr=max(7, 5+9)=14, prev2=7, prev1=14
    // Sub1 [1,5,4,2]: i=0: prev2=1,prev1=5; i=2(4): curr=max(5,1+4)=5,prev2=5,prev1=5; i=3(2): curr=max(5,5+2)=7
    // Answer: max(7, 14) = 14
    // [6, 1, 9, 1, 6] (circular): the obvious greedy of "start from the largest (9)
    // then take its non-adjacent neighbours" yields 9 + 6 = 15 only if you happen to
    // skip the circular constraint; a greedy that just takes every other house left-to-right
    // gets 6 + 9 + 6 = 21 which is invalid (idx 0 and idx 4 are adjacent).
    // The DP correctly produces 15: rob idx 0 and idx 2 (6+9) via sub-problem [6,1,9,1].
    @Test
    void greedyFailCase() {
        assertEquals(15, HouseRobbing.maxLoot(new int[]{6, 1, 9, 1, 6}));
    }

    @Test
    void allZeroesReturnsZero() {
        assertEquals(0, HouseRobbing.maxLoot(new int[]{0, 0, 0, 0}));
    }

    // [2, 7, 9, 3, 1] (circular): optimal is rob idx 0 and idx 2: 2 + 9 = 11.
    @Test
    void largerCircularCase() {
        assertEquals(11, HouseRobbing.maxLoot(new int[]{2, 7, 9, 3, 1}));
    }
}
