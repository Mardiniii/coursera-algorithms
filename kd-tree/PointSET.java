/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 07/02/2019
 *  Description: `PointSET` is a mutable datatyp that represents a set of points
 *  in the unit square by using a red-black BST.
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.Iterator;

public class PointSET {
    private SET<Point2D> tree;

    // Initialize and empty `PointSET data structure.
    public PointSET() {
        this.tree = new SET<Point2D>();
    }

    // Return `true` or `false` if the `PointSET` is empty or not.
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    // Return the number of points in the `PointSET`.
    public int size() {
        return tree.size();
    }

    // Add the given point to the `PointSET` if the key is not already present
    // in the set.
    public void insert(Point2D p) {
        tree.add(p);
    }

    // Returns `true` or `false` if the `PointSET` contains the given point.
    public boolean contains(Point2D p) {
        return tree.contains(p);
    }

    // Draw all points to standard draw.
    public void draw() {
        Iterator<Point2D> i = tree.iterator();

        while (i.hasNext()) {
            Point2D p = i.next();
            p.draw();
        }
    }

    // Returns an `Iterable<Point2D>` with all the points that are inside the
    // rectangle (or on the boundary).
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> points = new Queue<Point2D>();
        Iterator<Point2D> i = tree.iterator();

        while (i.hasNext()) {
            Point2D p = i.next();
            if (rect.contains(p)) points.enqueue(p);
        }

        return points;
    }

    // Returns the nearest neighbor in the set to point p and null if the set is
    // empty.
    public Point2D nearest(Point2D p) {
        Iterator<Point2D> i = tree.iterator();

        Point2D currentPoint = i.next();
        Point2D nearest = currentPoint;

        double minimumDistance = p.distanceTo(currentPoint);

        while (i.hasNext()) {
            currentPoint = i.next();
            double currentDistance = p.distanceTo(currentPoint);

            if (currentDistance < minimumDistance) {
                minimumDistance = currentDistance;
                nearest = currentPoint;
            }
        }

        return nearest;
    }

    public static void main(String[] args) {

    }
}
