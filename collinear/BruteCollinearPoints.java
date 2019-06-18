/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/12/2019
 *  Description: Examines 4 points at a time and checks whether they all lie on
 *  the same line segment, returning all such line segments.
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private int numberOfSegments;
    private LineSegment[] lineSegments = new LineSegment[1];

    // Create a new instance and find all the collinear points in the array.
    public BruteCollinearPoints(Point[] pointsArray) {
        // Check if the `pointsArray` is a null argument
        if (pointsArray == null)
            throw new IllegalArgumentException("The points argument is invalid, null value passed!");

        int numberOfPoints = pointsArray.length;
        Point[] points = new Point[numberOfPoints];

        // Check for any null elements in the `pointsArray`
        for (int i = 0; i < points.length; i++) {
            if (pointsArray[i] == null)
                throw new IllegalArgumentException("Invalid point element, it contains null values!");
            points[i] = pointsArray[i];
        }

        // Sort array before validating for duplicated points.
        Arrays.sort(points);

        // Check if the `points` contains duplicated elements.
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Duplicated point elements!");
        }

        numberOfSegments = 0;

        // Secondary approach to hava dynamic array. This library hasn't been in-
        // troduced yet at this part of the course.
        // List<LineSegment> list = new ArrayList<LineSegment>();

        for (int i = 0; i < numberOfPoints; i++) {
            for (int j = i+1; j < numberOfPoints; j++) {
                for (int k = j+1; k < numberOfPoints; k++) {
                    for (int m = k+1; m < numberOfPoints; m++) {
                        if (points[i].slopeOrder().compare(points[j], points[k]) == 0 &&
                                points[i].slopeOrder().compare(points[k], points[m]) == 0) {
                            // Secondary approach to dynamic array.
                            // list.add(new LineSegment(points[i], points[m]));

                            // Increment the number of segments and resize the array
                            numberOfSegments += 1;
                            resizeArray(numberOfSegments);

                            // Include the new `lineSegment`
                            lineSegments[numberOfSegments - 1] = new LineSegment(points[i], points[m]);
                        }
                    }
                }
            }
        }

        // Secondary approach to dynamic array.
        // lineSegments = list.toArray( new LineSegment[list.size()]);
    }

    // Return the number of segments detected by the constructor.
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // Return the collinear segments detected by the constructor.
    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, numberOfSegments);
    }

    // Resize the lineSegments array to the given `size` argument passed as a
    // parameter.
    private void resizeArray(int size) {
        LineSegment[] temp = new LineSegment[size];

        for (int i = 0; i < lineSegments.length; i++) {
            temp[i] = lineSegments[i];
        }
        lineSegments = temp;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(2, 6);
        Point p3 = new Point(3, 9);
        Point p4 = new Point(4, 12);
        Point p5 = new Point(5, 15);
        Point p6 = new Point(6, 18);
        Point p7 = new Point(7, 21);
        Point p8 = new Point(8, 24);
        Point[] pointsFirstTest = {p1, p2, p3, p4, p5, p6, p7, p8};

        BruteCollinearPoints bcp = new BruteCollinearPoints(pointsFirstTest);

        for (int i = 0; i < bcp.segments().length; i++) {
            LineSegment segment = bcp.segments()[i];

            System.out.println("Segments: " + segment.toString());
        }

        System.out.println("Number of segments: " + bcp.numberOfSegments());
        System.out.println("Number of segments: " + bcp.segments().length);

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] pointsSecondTest = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            pointsSecondTest[i] = new Point(x, y);
        }

        // draw the pointsSecondTest
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : pointsSecondTest) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(pointsSecondTest);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
