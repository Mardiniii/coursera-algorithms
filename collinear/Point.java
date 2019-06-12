/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        double slope;
        boolean sameX = this.x == that.x;
        boolean sameY = this.y == that.y;

        if (sameX && sameY) slope = Double.NEGATIVE_INFINITY;
        else if (sameX) slope = Double.POSITIVE_INFINITY;
        else if (sameY) slope = +0.0;
        else slope = (double) (that.y - this.y) / (double) (that.x - this.x);

        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            double slope1 = Point.this.slopeTo(p1);
            double slope2 = Point.this.slopeTo(p2);

            if (slope1 < slope2) return -1;
            if (slope1 > slope2) return +1;
            return 0;
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p1 = new Point(1,5);
        Point p2 = new Point(1,3);
        Point p3 = new Point(3,1);
        Point p4 = new Point(5,1);

        System.out.println("Slope to itslef: " + p1.slopeTo(p1));
        System.out.println("Vertical slope: "+ p1.slopeTo(p2));
        System.out.println("Horizontal slope: " + p3.slopeTo(p4));
        System.out.println("Regular slope: " + p1.slopeTo(p4));
        System.out.println("Regular slope: " + p2.slopeTo(p4));

        System.out.println("p1 compared to itslef returns 0: " + p1.compareTo(p1));
        System.out.println("p1 compared to p2 returns +1: " + p1.compareTo(p2));
        System.out.println("p2 compared to p1 returns -1: " + p2.compareTo(p1));
        System.out.println("p3 compared to p4 returns -1: " + p3.compareTo(p4));

        System.out.println("slopeOrder comparator returns +1: " + p1.slopeOrder().compare(p2, p3));
        System.out.println("slopeOrder comparator returns -1: " + p1.slopeOrder().compare(p3, p2));
        System.out.println("slopeOrder comparator returns 0: " + p1.slopeOrder().compare(p2, p2));
    }
}
