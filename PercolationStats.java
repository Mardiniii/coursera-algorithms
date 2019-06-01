/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 01/06/2018
 *  Description: PercolationsStats performs several percolation grids to find
 *  the percolation treshold from each of them. It opens an API for computing
 *  some stats variables like the mean, standard deviation and the 95% confidence
 *  percentil.
 **************************************************************************** */

public class PercolationStats {
    private int N;                   // Grid size
    private int trials;              // Number of experiments
    private int[] experimentResults; // Number of open sites when grid percolated

    private void validInput(int n, int numberOfTrials) {
        if (n < 1 && numberOfTrials < 1) {
            throw new IllegalArgumentException("n or trials are less than one. n: " + n + ", trials: " + numberOfTrials);
        }
    }

    private int randomNumber() {
        return StdRandom.uniform(N) + 1;
    }

    public static void main(String[] args) {

    }
}
