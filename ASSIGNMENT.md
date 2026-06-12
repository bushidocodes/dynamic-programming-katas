# Coding Project 2 – Dynamic Programming

**Due Date:** March 17th, 2019

## Instructions

Choose two of the following problems and use dynamic programming to solve them. Please write your code in Java. When submitting, please submit your original code file, not pasting them to a word document or PDF. This is a group project; only one submission is needed per group. Extra points will be given to a better interface.

---

## Problem 1: House Robbing

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

**Examples:**

```
Input:  2, 3, 2
Output: 3

Input:  1, 2, 3, 1
Output: 4
```

---

## Problem 2: Wildcard Pattern Matching

Given a string and a pattern containing wildcard characters `*` and `?`, where `?` can match any single character and `*` can match any number of characters (including zero), design an efficient algorithm to find if the pattern matches the complete input string.

**Examples:**

```
Input:  string = "xyxzzxy", pattern = "x***y"
Output: match

Input:  string = "xyxzzxy", pattern = "x***x"
Output: no match

Input:  string = "xyxzzxy", pattern = "x***x?"
Output: match
```

---

## Problem 3: Maximum Points (Coin Picking Game)

Consider a row of `n` coins of values `v1, …, vn`, where `n` is even. We play a game against an opponent by alternating turns. In each turn, a player selects either the first or last coin from the row, removes it from the row permanently, and receives the value of that coin. Determine the maximum possible amount of points we can definitely win if we move first.

> **Note:** The opponent plays optimally.

**Examples:**

```
Coins: 5, 3, 7, 10  →  User collects 10 and 5, maximum points: 15
Coins: 8, 15, 3, 7  →  User collects 7 and 15, maximum points: 22
```

---

## Problem 4: Chess Knight

Given a square chessboard of N×N size, the position of a Knight and the position of a target are given. Find the minimum number of steps the Knight will take to reach the target position.

**Example:**

```
Knight position: (4, 5)
Target position: (1, 1)
Number of steps: 3

Path: (4, 5) → (5, 3) → (3, 2) → (1, 1)
```
