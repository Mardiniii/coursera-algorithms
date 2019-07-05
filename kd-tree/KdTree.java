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
        boolean division;
        Node left;
        Node right;

        public Node(Point2D p) {
            this.point =  p;
        }
    }

    // Create a new `KdTree` object with a `null` root.
    public KdTree() {
        root = null;
    }

    // Return `true` or `false` if the `KdTree` is empty or not.
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of points in the `KdTree`.
    public int size() {
        return size;
    }

    // Add the given point to the `KdTree` if the key is not already present
    // in the set.
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }

        Node newNode = new Node(p);

        if (root == null) {
            root = newNode;
            root.division = VERTICAL;
            size++;
            return;
        }

        Node currentNode = root;

        while(true) {
            if (p.equals(currentNode.point)) {
                return;
            }

            if (currentNode.division == VERTICAL) {
                if (p.x() < currentNode.point.x()) {
                    if (currentNode.left == null) {
                        newNode.division = HORIZONTAL;
                        currentNode.left = newNode;
                        size++;
                        return;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    if (currentNode.right == null) {
                        newNode.division = HORIZONTAL;
                        currentNode.right = newNode;
                        size++;
                        return;
                    } else {
                        currentNode = currentNode.right;
                    }
                }

            } else {
                if (p.y() < currentNode.point.y()) {
                    if (currentNode.left == null) {
                        newNode.division = VERTICAL;
                        currentNode.left = newNode;
                        size++;
                        return;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    if (currentNode.right == null) {
                        newNode.division = VERTICAL;
                        currentNode.right = newNode;
                        size++;
                        return;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Point2D p0 = new Point2D(0, 0);
        Point2D p1 = new Point2D(0.2, 0.2);
        Point2D p2 = new Point2D(0.2, 0.4);
        Point2D p3 = new Point2D(0.3, 0.2);
        Point2D p4 = new Point2D(0.5, 0.5);
        Point2D p5 = new Point2D(1.0, 1.0);

        KdTree kdTree = new KdTree();
        kdTree.insert(p1);
        kdTree.insert(p2);
        kdTree.insert(p3);
        kdTree.insert(p4);
        kdTree.insert(p5);

        System.out.println("Is the Kd Tree empty?: " + kdTree.isEmpty());
        System.out.println("KdTree size: " + kdTree.size());
    }
}
