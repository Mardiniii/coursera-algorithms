/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 07/04/2019
 *  Description: `KdTree` is a mutable datatype that represents a set of points
 *  in the unit square by using a 2d-Tree.
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static final boolean VERTICAL   = true;
    private static final boolean HORIZONTAL = false;

    private int size;
    private Node root;
    private Queue<Point2D> pointsInRange;

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

    // Returns `true` or `false` if the `KdTree` contains the given point.
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }

        Node currentNode = root;

        while(currentNode != null) {
            if (p.equals(currentNode.point)) return true;

            if (currentNode.division == VERTICAL) {
                if (p.x() < currentNode.point.x()) currentNode = currentNode.left;
                else currentNode = currentNode.right;
            } else {
                if (p.y() < currentNode.point.y()) currentNode = currentNode.left;
                else currentNode = currentNode.right;
            }
        }

        return false;
    }

    // Draw all points and divisions lines to standard draw.
    public void draw() {
        if (root != null) {
            drawNode(root, new RectHV(0, 0, 1, 1));
        }
    }

    // Returns an `Iterable<Point2D>` with all the points that are inside the
    // rectangle (or on the boundary).
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException("Argument cannot be null!");
        }

        pointsInRange = new Queue<Point2D>();

        searchPointsInRange(root, rect);

        return pointsInRange;
    }

    private void drawNode(Node n, RectHV rect) {
        StdDraw.setPenRadius(0.010);
        n.point.draw();

        StdDraw.setPenRadius();

        if (n.division == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.point.x(), rect.ymin(), n.point.x(), rect.ymax());
            StdDraw.setPenColor();

            if (n.left != null) {
                drawNode(n.left, new RectHV(rect.xmin(), rect.ymin(), n.point.x(), rect.ymax()));
            }

            if (n.right != null) {
                drawNode(n.right, new RectHV(n.point.x(), rect.ymin(), rect.xmax(), rect.ymax()));
            }
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rect.xmin(), n.point.y(), rect.xmax(), n.point.y());
            StdDraw.setPenColor();

            if (n.left != null) {
                drawNode(n.left, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), n.point.y()));
            }

            if (n.right != null) {
                drawNode(n.right, new RectHV(rect.xmin(), n.point.y(), rect.xmax(), rect.ymax()));
            }
        }
    }

    private void searchPointsInRange(Node node, RectHV rect) {
        if (node == null) return;

        if (node.division == VERTICAL) {
            if (node.point.x() > rect.xmax()) {
                searchPointsInRange(node.left, rect);
            } else if (node.point.x() < rect.xmin()) {
                searchPointsInRange(node.right, rect);
            } else {
                searchPointsInRange(node.left, rect);
                searchPointsInRange(node.right, rect);

                if (rect.contains(node.point)) pointsInRange.enqueue(node.point);
            }
        } else {
            if (node.point.y() > rect.ymax()) {
                searchPointsInRange(node.left, rect);
            } else if (node.point.y() < rect.ymin()) {
                searchPointsInRange(node.right, rect);
            } else {
                searchPointsInRange(node.left, rect);
                searchPointsInRange(node.right, rect);

                if (rect.contains(node.point)) pointsInRange.enqueue(node.point);
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

        // Point2D p0 = new Point2D(0, 0);
        // Point2D p1 = new Point2D(0.7, 0.2);
        // Point2D p2 = new Point2D(0.5, 0.4);
        // Point2D p3 = new Point2D(0.2, 0.3);
        // Point2D p4 = new Point2D(0.4, 0.7);
        // Point2D p5 = new Point2D(0.9, 0.6);

        KdTree kdTree = new KdTree();

        System.out.println("Is the Kd Tree empty? (true): " + kdTree.isEmpty());
        System.out.println("Does the empty Kd-Tree contain p1? (false): " + kdTree.contains(p1));

        kdTree.insert(p1);
        kdTree.insert(p2);
        kdTree.insert(p3);
        kdTree.insert(p4);
        kdTree.insert(p5);

        System.out.println("Is the Kd Tree empty? (false): " + kdTree.isEmpty());
        System.out.println("KdTree size (5): " + kdTree.size());
        System.out.println("Does the Kd-Tree contain p0? (false): " + kdTree.contains(p0));
        System.out.println("Does the Kd-Tree contain p1? (true): " + kdTree.contains(p1));

        RectHV rect = new RectHV(0.1, 0.1, 0.4, 0.3);
        System.out.println("Search range in rectangle: " + rect);
        Iterable<Point2D> range = kdTree.range(rect);

        for (Point2D p : range) {
            System.out.println(p);
        }

        kdTree.draw();
    }
}
