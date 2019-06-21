/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/18/2019
 *  Description: Board is an abstraction for representing a n-by-n array of blocks.
 *  This abstraction is used to solve the 8-Puzzle problem by using MinPQ and MaxPQ.
 **************************************************************************** */

public class Board {
    int n; // Board dimension
    int[][] grid;

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
        int[] firstRow  = {4, 0, 5};
        int[] secondRow = {1, 3, 8};
        int[] thirdRow  = {7, 6, 2};

        // Hamming = 6, Manhattan = 9
        // int[] firstRow  = {1, 0, 2};
        // int[] secondRow = {7, 5, 4};
        // int[] thirdRow  = {8, 6, 3};

        // Goal board
        // Hamming = 0, Manhattan = 0
        // int[] firstRow  = {1, 2, 3};
        // int[] secondRow = {4, 5, 6};
        // int[] thirdRow  = {7, 8, 0};

        int[][] blocks = {firstRow, secondRow, thirdRow};

        Board myBoard = new Board(blocks);

        String stringBoard = myBoard.toString();

        System.out.println(stringBoard);
        System.out.println(myBoard.hamming());
        System.out.println(myBoard.manhattan());
        System.out.println(myBoard.isGoal());
    }
}
