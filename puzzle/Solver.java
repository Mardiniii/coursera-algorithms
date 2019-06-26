/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/24/2019
 *  Description: Solver is an abstraction to solve the 8-Puzzle problem by using
 *  the A* algorithm.
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;

public class Solver {
    private MinPQ<SearchNode> minPriorityQueue;
    private SearchNode initialSearchNode;
    private int moves;

    private static class SearchNode {
        public static final Comparator<SearchNode> BY_MANHATTAN = new ByManhattan();
        public static final Comparator<SearchNode> BY_HAMMING = new ByHamming();

        private int moves;
        private Board board;
        private SearchNode next;

        private static class ByManhattan implements Comparator<SearchNode> {
            public int compare(SearchNode a, SearchNode b) {
                int aPriority = a.board.manhattan() + a.moves;
                int bPriority = b.board.manhattan() + b.moves;

                return aPriority.compareTo(bPriority);
            }
        }

        private static class ByHamming implements Comparator<SearchNode> {
            public int compare(SearchNode a, SearchNode b) {
                int aPriority = a.board.hamming() + a.moves;
                int bPriority = b.board.hamming() + b.moves;

                return aPriority.compareTo(bPriority);
            }
        }
    }

    public Solver(Board initialBoard) {
        if (initialBoard == null) {
            throw new IllegalArgumentException("Initial board cannot be null!");
        }
    }

    public static void main(String[] args) {
    }
}
