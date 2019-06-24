/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/18/2019
 *  Description: Board is an abstraction for representing a n-by-n array of blocks.
 *  This abstraction is used to solve the 8-Puzzle problem by using MinPQ and MaxPQ.
 **************************************************************************** */

import java.util.Arrays;

public class Board {
    private final int n; // Board dimension
    private final int[][] grid;

    // Construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        this.n = blocks.length;
        this.grid = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                grid[i][j] = blocks[i][j];
            }
        }
    }

    // Returns board dimension
    public int dimension() {
        return n;
    }


    // Returns number of block out of place
    public int hamming() {
        int expectedBlock = 1;
        int blocksCounter = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] != 0 && grid[i][j] != expectedBlock)
                    blocksCounter++;

                expectedBlock++;
            }
        }

        return blocksCounter;
    }

    // Returns the sum of Manhattan distances((sum of the vertical and horizontal
    // distance) between blocks and goal
    public int manhattan() {
        int manhattanDistanceSum = 0;
        int expectedBlock = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int currentBlock = grid[i][j];

                if(currentBlock != 0 && currentBlock != expectedBlock) {
                    int targetX = (currentBlock - 1) / n;
                    int targetY = (currentBlock - 1) % n;
                    int dx = i - targetX;
                    int dy = j - targetY;
                    manhattanDistanceSum += Math.abs(dx) + Math.abs(dy);
                }

                expectedBlock++;
            }
        }

        return manhattanDistanceSum;
    }

    // Returns `true` or `false` if this board is the goal board.
    public boolean isGoal() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int currentBlock = grid[i][j];
                int targetX = (currentBlock - 1) / n;
                int targetY = (currentBlock - 1) % n;

                if (currentBlock != 0 && targetX != i && targetY != j)
                    return false;
            }
        }

        return true;
    }

    public Board twin() {
        int blocks[][] = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                blocks[i][j] = grid[i][j];
            }
        }

        outerloop:
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int currentBlock = blocks[i][j];

                if (currentBlock != 0 && j + 1 < n && blocks[i][j+1] != 0) {
                    int temp = blocks[i][j+1];

                    blocks[i][j+1] = currentBlock;
                    blocks[i][j] = temp;
                    break outerloop;
                }
            }
        }

        return new Board(blocks);
    }

    // Returns `true` if this object is equal to y
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (this.n != that.n) return false;

        return Arrays.deepEquals(this.grid, that.grid);
    }

    // String representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", grid[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        // Hamming = 7, Manhattan = 13
        int[] firstRow1  = {4, 0, 5};
        int[] secondRow1 = {1, 3, 8};
        int[] thirdRow1  = {7, 6, 2};

        // Hamming = 6, Manhattan = 9
        int[] firstRow2  = {1, 0, 2};
        int[] secondRow2 = {7, 5, 4};
        int[] thirdRow2  = {8, 6, 3};

        int[] firstRow3  = {4, 0, 5};
        int[] secondRow3 = {1, 3, 8};
        int[] thirdRow3  = {7, 6, 2};

        // Goal board
        // Hamming = 0, Manhattan = 0
        // int[] firstRow  = {1, 2, 3};
        // int[] secondRow = {4, 5, 6};
        // int[] thirdRow  = {7, 8, 0};

        int[][] blocks1 = {firstRow1, secondRow1, thirdRow1};
        int[][] blocks2 = {firstRow2, secondRow2, thirdRow2};
        int[][] blocks3 = {firstRow3, secondRow3, thirdRow3};

        Board myFirstBoard = new Board(blocks1);
        Board mySecondBoard = new Board(blocks2);
        Board myThirdBoard = new Board(blocks3);

        String stringBoard = myFirstBoard.toString();

        System.out.println(stringBoard);
        System.out.println(myFirstBoard.hamming());
        System.out.println(myFirstBoard.manhattan());
        System.out.println(myFirstBoard.isGoal());

        Board myTwin = myFirstBoard.twin();

        String stringTwin = myTwin.toString();
        System.out.println(stringTwin);

        System.out.println("Is myFirstBoard equals to myFirstBoard?: " + myFirstBoard.equals(myFirstBoard));
        System.out.println("Is myFirstBoard equals to mySecondBoard?: " + myFirstBoard.equals(mySecondBoard));
        System.out.println("Is myFirstBoard equals to myThirdBoard?: " + myFirstBoard.equals(myThirdBoard));
        System.out.println("Is myFirstBoard equals to null?: " + myFirstBoard.equals(mySecondBoard));
    }
}
