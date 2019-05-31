/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 05/30/2019
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int [][] grid;
    private WeightedQuickUnionUF wquUF;

//    public    void open(int row, int col);    // open site (row, col) if it is not open already
//    public boolean isFull(int row, int col)  // is site (row, col) full?
//    public     int numberOfOpenSites()       // number of open sites
//    public boolean percolates()              // does the system percolate?

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("grid size: n <= 0");

        // n * n sites plus two additional sites for top and bottom virtual sites
        int sitesNumber = n * n;
        int topVirtualSiteIndex = sitesNumber;
        int bottomVirtualSiteIndex = sitesNumber+1;

        N = n;
        grid = new int[n+1][n+1];
        wquUF = new WeightedQuickUnionUF(sitesNumber + 2);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = 0;

                // In the cases where i == 1 || i == n, we want to connect these
                // sites with the top and bottom virtual components.
                if (i == 1) { wquUF.union(xyTo1D(i,j), topVirtualSiteIndex); }
                else if (i == n) { wquUF.union(xyTo1D(i,j), bottomVirtualSiteIndex); }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col] == 1;
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * N + col;
    }

    private boolean validIndices(int row, int col) {
        return row < 1 || row > N || col < 1 || col > N;
    }

    private void printPercolationMatrix() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        int xyToId;

        Percolation perc = new Percolation(4);
        perc.printPercolationMatrix();

        xyToId = perc.xyTo1D(2,2);
        System.out.println("Row 2 and col 2 are position "+xyToId+" in array.");

        xyToId = perc.xyTo1D(3,3);
        System.out.println("Row 2 and col 2 are position "+xyToId+" in array.");

        xyToId = perc.xyTo1D(4,4);
        System.out.println("Row 2 and col 2 are position "+xyToId+" in array.");
    }
}
