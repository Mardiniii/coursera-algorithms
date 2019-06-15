/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/14/2019
 *  Description: Examines 4 points at a time and checks whether they all lie on
 *  the same line segment, returning all such line segments.
 **************************************************************************** */

import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private final Point[] points;
    private final int numberOfPoints;
    private int numberOfSegments = 0;
    private LineSegment[] lineSegments = new LineSegment[1];

    public FastCollinearPoints(Point[] pointsArray) {
        validatePoints(pointsArray);

        points = pointsArray;
        numberOfPoints = points.length;

        Arrays.sort(pointsArray);

        Point[] temp = Arrays.copyOf(points, points.length);

        for (int i = 0; i < numberOfPoints; i++) {
            Arrays.sort(temp, points[i].slopeOrder());
            Point firstPoint = points[i];
            Point lastPoint = points[i];
            int pointsCounter = 2;

            for (int j = 0; j < numberOfPoints - 1; j++) {
                if (points[i].slopeTo(points[j + 1]) == points[j].slopeTo(points[j + 1])) {
                    if (temp[j + 1].compareTo(points[i]) > 0) {
                        lastPoint = temp[j+1];
                    } else if ((temp[j + 1].compareTo(points[i]) < 0)) {
                        firstPoint = temp[j+1];
                    }
                    pointsCounter++;

                    if (j == numberOfPoints - 2 && pointsCounter >= 4 && points[i].compareTo(firstPoint) == 0) {
                        // Increment the number of segments and resize the array
                        resizeArray(numberOfSegments += 1);

                        // Include the new `lineSegment`
                        lineSegments[numberOfSegments-1] = new LineSegment(firstPoint, lastPoint);
                    }

                } else {
                    if (pointsCounter >= 4 && points[i].compareTo(firstPoint) == 0) {
                        // Increment the number of segments and resize the array
                        resizeArray(numberOfSegments += 1);

                        // Include the new `lineSegment`
                        lineSegments[numberOfSegments-1] = new LineSegment(firstPoint, lastPoint);
                    }
                    if (points[i].compareTo(temp[j + 1]) > 0) {
                        lastPoint = points[i];
                        firstPoint = temp[j + 1];
                        pointsCounter = 2;
                    } else {
                        lastPoint = temp[j + 1];
                        firstPoint = points[i];
                        pointsCounter = 2;
                    }
                }
            }
        }
    }

    // Return the number of segments detected by the constructor.
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // Return the collinear segments detected by the constructor.
    public LineSegment[] segments() {
        return lineSegments;
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

    private void validatePoints(Point[] pointsArray) {
        // Check if the `pointsArray` is a null argument
        if (pointsArray == null) {
            throw new IllegalArgumentException("The points argument is invalid, null value passed!");
        }

        // Check for any null elements in the `pointsArray`
        for (int i = 0; i < pointsArray.length; i++) {
            if (pointsArray[i] == null) {
                throw new IllegalArgumentException("Invalid point element, it contains null values!");
            }
        }

        // Check if the `pointsArray` contains duplicated elements.
        for (int i = 0; i < pointsArray.length; i++) {
            for (int j = i + 1; j < pointsArray.length; j++) {
                if (pointsArray[i] == pointsArray[j]) {
                    throw new IllegalArgumentException("Duplicated point elements!");
                }
            }
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
