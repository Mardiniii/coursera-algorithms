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
    private int N;                   // Grid size
    private int trials;              // Number of experiments
    private double[] experimentResults; // Number of open sites when grid percolated

    public PercolationStats(int n, int numberOfTrials) {
        validInput(n, numberOfTrials);

        N = n;
        trials = numberOfTrials;
        experimentResults = new double[trials];

        int numberOfSites = N * N;

        for(int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int row = randomNumber();
                int col = randomNumber();

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    if (perc.percolates()) {
                        double treshold = (double)perc.numberOfOpenSites() / numberOfSites;
                        experimentResults[i] = treshold;
                    }
                }
            }
        }
    }

    public double mean() {
        return StdStats.mean(experimentResults);
    }

    public double stddev() {
        if (trials == 1) return Double.NaN;

        return StdStats.stddev(experimentResults);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    private void validInput(int n, int numberOfTrials) {
        if (n < 1 && numberOfTrials < 1) {
            throw new IllegalArgumentException("n or trials are less than one. n: " + n + ", trials: " + numberOfTrials);
        }
    }

    private int randomNumber() {
        return StdRandom.uniform(N) + 1;
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
