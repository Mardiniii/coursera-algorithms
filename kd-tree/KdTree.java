/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 07/04/2019
 *  Description: `KdTree` is a mutable datatype that represents a set of points
 *  in the unit square by using a 2d-Tree.
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;

public class KdTree {
    private static final boolean VERTICAL   = true;
    private static final boolean HORIZONTAL = false;

    private int size;
    private Node root;

    private static class Node {
        Point2D point;
        boolean isVertical;
        Node left;
        Node right;
    }

    // Return `true` or `false` if the `Kd-Tree` is empty or not.
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of points in the `Kd-Tree`.
    public int size() {
        return size;
    }

    public static void main(String[] args) {

    }
}
