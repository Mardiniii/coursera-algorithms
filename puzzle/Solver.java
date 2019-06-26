/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/24/2019
 *  Description: Solver is an abstraction to solve the 8-Puzzle problem by using
 *  the A* algorithm.
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;
import java.util.Comparator;

public class Solver {
    private MinPQ<SearchNode> minPriorityQueue;
    private SearchNode initialSearchNode;
    private int moves;

    private static class SearchNode {
        public static final Comparator<SearchNode> BY_MANHATTAN_PRIORITY = new ByManhattanPriority();
        public static final Comparator<SearchNode> BY_HAMMING_PRIORITY = new ByHammingPriority();

        private int moves;
        private Board board;
        private SearchNode next;

        private static class ByManhattanPriority implements Comparator<SearchNode> {
            public int compare(SearchNode a, SearchNode b) {
                int aPriority = a.board.manhattan() + a.moves;
                int bPriority = b.board.manhattan() + b.moves;

                if (aPriority < bPriority) return -1;
                if (aPriority > bPriority) return 1;
                return 0;
            }
        }

        private static class ByHammingPriority implements Comparator<SearchNode> {
            public int compare(SearchNode a, SearchNode b) {
                int aPriority = a.board.hamming() + a.moves;
                int bPriority = b.board.hamming() + b.moves;

                if (aPriority < bPriority) return -1;
                if (aPriority > bPriority) return 1;
                return 0;
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
