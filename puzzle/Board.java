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
        int[] firstRow  = {1, 2, 3};
        int[] secondRow = {4, 5, 6};
        int[] thirdRow  = {7, 8, 9};

        int[][] blocks = {firstRow, secondRow, thirdRow};

        Board myBoard = new Board(blocks);

        String stringBoard = myBoard.toString();

        System.out.println(stringBoard);
    }
}
