/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/24/2019
 *  Description: Solver is an abstraction to solve the 8-Puzzle problem by using
 *  the A* algorithm.
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;

public class Solver {
    private static class SearchNode {
        public static final Comparator<searchNode> BY_MANHATTAN = new ByManhattan();
        public static final Comparator<searchNode> BY_HAMMING = new ByHamming();

        private int moves;
        private Board board;
        private searchNode next;

        private static class ByManhattan implements Comparator<searchNode> {
            public int compare(searchNode a, searchNode b) {
                int aPriority = a.board.manhattan() + a.moves;
                int bPriority = b.board.manhattan() + b.moves;

                return aPriority.compareTo(bPriority);
            }
        }

        private static class ByHamming implements Comparator<searchNode> {
            public int compare(searchNode a, searchNode b) {
                int aPriority = a.board.hamming() + a.moves;
                int bPriority = b.board.hamming() + b.moves;

                return aPriority.compareTo(bPriority);
            }
        }
    }

    public static void main(String[] args) {
    }
}
