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
    private SearchNode lastSearchNode;
    private int moves;

    public Solver(Board initialBoard) {
        if (initialBoard == null) {
            throw new IllegalArgumentException("Initial board cannot be null!");
        }

        SearchNode initialSearchNode     = new SearchNode(initialBoard);
        SearchNode initialTwinSearchNode = new SearchNode(initialBoard.twin());

        MinPQ<SearchNode> minPQ     = new MinPQ<SearchNode>(SearchNode.BY_MANHATTAN_PRIORITY);
        MinPQ<SearchNode> minTwinPQ = new MinPQ<SearchNode>(SearchNode.BY_MANHATTAN_PRIORITY);

        minPQ.insert(initialSearchNode);
        minTwinPQ.insert(initialTwinSearchNode);

        while (true) {
            lastSearchNode = solve(minPQ);

            if (lastSearchNode != null || solve(minTwinPQ) != null) return;

        }
    }

    public boolean isSolvable() {
        return lastSearchNode != null;
    }

    private static class SearchNode {
        public static final Comparator<SearchNode> BY_MANHATTAN_PRIORITY = new ByManhattanPriority();
        public static final Comparator<SearchNode> BY_HAMMING_PRIORITY = new ByHammingPriority();

        private int moves;
        private Board board;
        private SearchNode previous;

        public SearchNode(Board board) {
            this.board = board;
        }

        public SearchNode(Board board, SearchNode previousSearchNode) {
            this.board = board;
            this.previous = previousSearchNode;
            this.moves = previousSearchNode.moves + 1;
        }

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

    private SearchNode solve(MinPQ<SearchNode> minPriorityQueue) {
        if (minPriorityQueue.isEmpty()) return null;
        SearchNode currentNode = minPriorityQueue.delMin();

        if (currentNode.board.isGoal()) return currentNode;

        for (Board board : currentNode.board.neighbors()) {
            if (currentNode.previous == null || !board.equals(currentNode.previous.board)) {
                minPriorityQueue.insert(new SearchNode(board, currentNode));
            }
        }

        return null;
    }

    public static void main(String[] args) {

    }
}
