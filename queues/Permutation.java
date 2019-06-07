/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/07/2019
 *  Description: Permutation  takes an integer k as a command-line argument;
 *  reads in a sequence of strings from standard input using StdIn.readString();
 *  and prints exactly k of them, uniformly at random. Print each item from the
 *  sequence at most once.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        // Read argument from the command line
        int numberOfPrints = Integer.parseInt(args[0]);

        // Initialize an empty randomized queue
        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();

        // Enqueue input in the randomized queue
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randomQueue.enqueue(s);
        }

        // Iterates up to numberOfPrints times
        Iterator<String> i = randomQueue.iterator();
        while (numberOfPrints > 0) {
            String s = i.next();
            System.out.println(s);
            numberOfPrints--;
        }
    }
}
