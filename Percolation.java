/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 05/30/2019
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int [][] grid;
    private int numberOfOpenSites = 0;
    private WeightedQuickUnionUF wquUF;

    private int sitesNumber;
    private int topVirtualSiteIndex;
    private int bottomVirtualSiteIndex;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("grid size: n <= 0");

        N = n;
        grid = new int[n+1][n+1];
        sitesNumber = N * N;
        topVirtualSiteIndex = sitesNumber;
        bottomVirtualSiteIndex = sitesNumber+1;

        // n * n sites plus two additional sites for top and bottom virtual sites
        wquUF = new WeightedQuickUnionUF(sitesNumber + 2);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void open(int row, int col) {
        checkIndices(row, col);

        if (!isOpen(row,col)) {
            grid[row][col] = 1;
            numberOfOpenSites++;
            int openedSiteIndex = xyTo1D(row, col);

            // Connect with neighbors
            if (validSiteForConnection(row, col - 1)) connectSites(openedSiteIndex, xyTo1D(row, col - 1));
            if (validSiteForConnection(row, col + 1)) connectSites(openedSiteIndex, xyTo1D(row, col + 1));
            if (validSiteForConnection(row - 1, col)) connectSites(openedSiteIndex, xyTo1D(row - 1, col));
            if (validSiteForConnection(row + 1, col)) connectSites(openedSiteIndex, xyTo1D(row + 1, col));

            // Connect with top and bottom virtual sites
            // In the cases where i == 1 || i == n, we want to connect these
            // sites with the top and bottom virtual components.
            if (row == 1) wquUF.union(xyTo1D(row, col), topVirtualSiteIndex);
            if (row == N) wquUF.union(xyTo1D(row, col), bottomVirtualSiteIndex);
        }
    }

    public boolean isOpen(int row, int col) {
        checkIndices(row, col);

        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        checkIndices(row, col);

        return grid[row][col] == 0;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return wquUF.connected((N * N), (N * N) + 1);
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * N + (col - 1);
    }

    private boolean validIndices(int row, int col) {
        return row >= 1 && row <= N && col >= 1 && col <= N;
    }

    private void checkIndices(int row, int col) {
        if (!validIndices(row, col)) {
            throw new IllegalArgumentException("Invalid indices, row: " + row + ", col: " + col);
        }
    }

    private void printPercolationMatrix() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    private boolean validSiteForConnection(int row, int col) {
        return validIndices(row, col) && isOpen(row, col);
    }

    private void connectSites(int siteIndexP, int siteIndexQ) {
        wquUF.union(siteIndexP, siteIndexQ);
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

        System.out.println("Is row 2 and col 1 open?: " + perc.isOpen(2, 1));
        System.out.println("Is row 1 and col 2 open?: " + perc.isOpen(1, 2));
        System.out.println("Is row 2 and col 3 open?: " + perc.isOpen(2, 3));
        System.out.println("Is row 3 and col 2 open?: " + perc.isOpen(3, 2));
        System.out.println("Is row 2 and col 2 open?: " + perc.isOpen(2, 2));
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());


        System.out.println("Opening row 2 and col 1...");
        perc.open(2,1);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());


        System.out.println("Opening row 1 and col 2...");
        perc.open(1,2);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());


        System.out.println("Opening row 2 and col 3...");
        perc.open(2,3);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());


        System.out.println("Opening row 3 and col 2...");
        perc.open(3,2);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());


        System.out.println("Is row 2 and col 1 open?: " + perc.isOpen(2, 1));
        System.out.println("Is row 1 and col 2 open?: " + perc.isOpen(1, 2));
        System.out.println("Is row 2 and col 3 open?: " + perc.isOpen(2, 3));
        System.out.println("Is row 3 and col 2 open?: " + perc.isOpen(3, 2));

        perc.printPercolationMatrix();

        System.out.println("Opening row 2 and col 2...");
        perc.open(2,2);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());

        perc.printPercolationMatrix();

        System.out.println("Is (2,1) connected to (2,2)?: " + perc.wquUF.connected(perc.xyTo1D(2, 1), perc.xyTo1D(2,2)));
        System.out.println("Is (1,2) connected to (2,2)?: " + perc.wquUF.connected(perc.xyTo1D(1, 2), perc.xyTo1D(2,2)));
        System.out.println("Is (2,3) connected to (2,2)?: " + perc.wquUF.connected(perc.xyTo1D(2, 3), perc.xyTo1D(2,2)));
        System.out.println("Is (3,2) connected to (2,2)?: " + perc.wquUF.connected(perc.xyTo1D(3, 2), perc.xyTo1D(2,2)));
        System.out.println("Is (3,2) connected to (1,2)?: " + perc.wquUF.connected(perc.xyTo1D(3, 2), perc.xyTo1D(1,2)));
        System.out.println("Is (2,1) connected to (2,3)?: " + perc.wquUF.connected(perc.xyTo1D(2, 1), perc.xyTo1D(2,3)));
        System.out.println("Is virtual top connected to (1,1)?: " + perc.wquUF.connected(16, 0));
        System.out.println("Is virtual top connected to (1,2)?: " + perc.wquUF.connected(16, 1));
        System.out.println("Is virtual top connected to (1,3)?: " + perc.wquUF.connected(16, 2));
        System.out.println("Is virtual top connected to (1,4)?: " + perc.wquUF.connected(16, 3));

        System.out.println("Is virtual bottom connected to (4,1)?: " + perc.wquUF.connected(17, 13));
        System.out.println("Is virtual bottom connected to (4,2)?: " + perc.wquUF.connected(17, 14));
        System.out.println("Is virtual bottom connected to (4,3)?: " + perc.wquUF.connected(17, 15));
        System.out.println("Is virtual bottom connected to (4,4)?: " + perc.wquUF.connected(17, 16));

        System.out.println("Opening row 4 and col 3...");
        perc.open(4,3);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());

        perc.printPercolationMatrix();

        System.out.println(perc.percolates());

        System.out.println("Opening row 4 and col 4...");
        perc.open(4,4);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());

        perc.printPercolationMatrix();

        System.out.println(perc.percolates());

        System.out.println("Opening row 4 and col 1...");
        perc.open(4,1);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());

        perc.printPercolationMatrix();

        System.out.println(perc.percolates());

        System.out.println("Opening row 4 and col 2...");
        perc.open(4,2);
        System.out.println("Number of open sites: " + perc.numberOfOpenSites());

        perc.printPercolationMatrix();

        System.out.println(perc.percolates());
    }
}
