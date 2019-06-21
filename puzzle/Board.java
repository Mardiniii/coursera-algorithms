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
        int[] firstRow  = {4, 0, 5};
        int[] secondRow = {1, 3, 8};
        int[] thirdRow  = {7, 6, 2};

        int[][] blocks = {firstRow, secondRow, thirdRow};

        Board myBoard = new Board(blocks);

        String stringBoard = myBoard.toString();

        System.out.println(stringBoard);
        System.out.println(myBoard.hamming());
    }
}
