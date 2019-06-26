/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/24/2019
 *  Description: Solver is an abstraction to solve the 8-Puzzle problem by using
 *  the A* algorithm.
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

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

    public boolean isSolvable() {
        return lastSearchNode != null;
    }

    public int moves() {
        if (isSolvable()) return lastSearchNode.moves;

        return -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> stack = new Stack<Board>();
        SearchNode currentNode = lastSearchNode;

        while(currentNode != null) {
            stack.push(currentNode.board);
            currentNode = currentNode.previous;
        }

        return stack;
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

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
