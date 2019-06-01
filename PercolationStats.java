/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 01/06/2018
 *  Description: PercolationsStats performs several percolation grids to find
 *  the percolation treshold from each of them. It opens an API for computing
 *  some stats variables like the mean, standard deviation and the 95% confidence
 *  percentil.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    static final double CONFIDENCE_95 = 1.96;

    private final int gridSize;                   // Grid size
    private final int trials;              // Number of experiments
    private double sttdev;
    private final double[] experimentResults; // Number of open sites when grid percolated

    public PercolationStats(int n, int numberOfTrials) {
        validInput(n, numberOfTrials);

        gridSize = n;
        trials = numberOfTrials;
        experimentResults = new double[trials];

        int numberOfSites = gridSize * gridSize;

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int row = randomNumber();
                int col = randomNumber();

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                }
            }

            double treshold = (double) perc.numberOfOpenSites() / numberOfSites;
            experimentResults[i] = treshold;
        }
    }

    public double mean() {
        return StdStats.mean(experimentResults);
    }

    public double stddev() {
        if (trials == 1) return Double.NaN;

        if (sttdev == 0.0) sttdev = StdStats.stddev(experimentResults);

        return sttdev;
    }

    public double confidenceLo() {
        return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
    }

    private void validInput(int n, int numberOfTrials) {
        if (n < 1 || numberOfTrials < 1) {
            throw new IllegalArgumentException("n or trials are less than one. n: " + n + ", trials: " + numberOfTrials);
        }
    }

    private int randomNumber() {
        return StdRandom.uniform(gridSize) + 1;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev:                 = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
