/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 05/30/2019
 *  Description: Percolation class in an abstraction to execute different operations
 *  between a given set of nodes. It also determines if a system percolates or not.
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;                      // Grid size
    private int [][] grid;              // Grid data structure
    private int numberOfOpenSites = 0;  // Number of opened sites
    private WeightedQuickUnionUF wquUF; // WeightedQuickUnionUF data structure

    private int sitesNumber;            // Number of sites
    private int topVirtualSiteIndex;    // Top virtual site
    private int bottomVirtualSiteIndex; // Bottom virtual site

    /*
    This constructur creates a int[N][N] with all the values set to 0 by
    default. It also creates a `WeightedQuickUnionUF` instance with two more
    sites for the top and bottom virtual nodes.
     */
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

    /*
    This method opens a new site if it is not open yet. It also creates the new
    connections with the open adjacents sites. In the case the (row, col) are
    located in the top or bottom rows it enables the conection with the virtual
    sites
     */
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

    /*
    This method returns a boolean equals to true if the site is open.
     */
    public boolean isOpen(int row, int col) {
        checkIndices(row, col);

        return grid[row][col] == 1;
    }

    /*
    This method returns a boolean equals to true if the site is closed.
     */
    public boolean isFull(int row, int col) {
        checkIndices(row, col);

        return grid[row][col] == 0;
    }

    /*
    This method returns the number of open sites in the grid.
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /*
    This method returns true if the top virtual site is connected to the top
    bottom site.
     */
    public boolean percolates() {
        return wquUF.connected((N * N), (N * N) + 1);
    }

    /*
    This method converts 2D coordinates from the grid to an 1D coordinate in the
    WeightedQuickUnionUF data structure.
     */
    private int xyTo1D(int row, int col) {
        return (row - 1) * N + (col - 1);
    }

    /*
    This method returns true if row and col are valid indices.
     */
    private boolean validIndices(int row, int col) {
        return row >= 1 && row <= N && col >= 1 && col <= N;
    }

    /*
    This method throws an exception if the row and col values are not valid.
     */
    private void checkIndices(int row, int col) {
        if (!validIndices(row, col)) {
            throw new IllegalArgumentException("Invalid indices, row: " + row + ", col: " + col);
        }
    }

    /*
    This method prints the percolation grid.
     */
    private void printPercolationMatrix() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    /*
    This method returns true if the row and col values are valid indices and the
    site at the (row, col) location is open.
     */
    private boolean validSiteForConnection(int row, int col) {
        return validIndices(row, col) && isOpen(row, col);
    }

    /*
    This method connect two sites. It delegates the operation to the
    `WeightedQuickUnionUF#union` method.
     */
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
