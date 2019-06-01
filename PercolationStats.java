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

import java.util.Arrays;

public class PercolationStats {
    private int N;                   // Grid size
    private int trials;              // Number of experiments
    private int[] experimentResults; // Number of open sites when grid percolated

    public PercolationStats(int n, int numberOfTrials) {
        validInput(n, numberOfTrials);

        N = n;
        trials = numberOfTrials;
        experimentResults = new int[trials];

        for(int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                int row = randomNumber();
                int col = randomNumber();

                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    if (perc.percolates()) experimentResults[i] = perc.numberOfOpenSites();
                }
            }
        }
    }

    public double mean() {
        return StdStats.mean(experimentResults);
    }

    public double stddev() {
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
        PercolationStats stats = new PercolationStats(15, 5);

        System.out.println(Arrays.toString(stats.experimentResults));
        System.out.println("Mean: " + stats.mean());
        System.out.println("Sample Standard Deviation: " + stats.stddev());
        System.out.println("Low endpoint of 95% confidence interval: " + stats.confidenceLo());
        System.out.println("High endpoint of 95% confidence interval: " + stats.confidenceHi());
    }
}