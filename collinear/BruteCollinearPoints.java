/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/12/2019
 *  Description: Examines 4 points at a time and checks whether they all lie on
 *  the same line segment, returning all such line segments.
 **************************************************************************** */

import java.util.Arrays;
import java.lang.IllegalArgumentException;

public class BruteCollinearPoints {
    private final Point[] points;
    private int numberOfSegments = 0;
    private LineSegment[] lineSegments = new LineSegment[1];

    // Create a new instance and find all the collinear points in the array.
    public BruteCollinearPoints(Point[] pointsArray) {
        validatePoints(pointsArray);

        points = pointsArray;
        Point[] temp = new Point[4];
        int numberOfPoints = points.length;
        // Secondary approach to hava dynamic array. This library hasn't been in-
        // troduced yet at this part of the course.
        // List<LineSegment> list = new ArrayList<LineSegment>();

        Arrays.sort(points);

        for (int i = 0; i < numberOfPoints; i++) {
            for (int j = i+1; j < numberOfPoints; j++) {
                for (int k = j+1; k < numberOfPoints; k++) {
                    for (int l = k+1; l < numberOfPoints; l++) {
                        temp[0] = points[i];
                        temp[1] = points[j];
                        temp[2] = points[k];
                        temp[3] = points[l];

                        double slope1 = temp[0].slopeTo(temp[1]);
                        double slope2 = temp[0].slopeTo(temp[2]);
                        double slope3 = temp[0].slopeTo(temp[3]);

                        if (slope1 == slope2 && slope2 == slope3) {
                            // Secondary approach to dynamic array.
                            // list.add(new LineSegment(temp[0], temp[3]));

                            // Increment the number of segments and resize the array
                            resizeArray(numberOfSegments += 1);

                            // Include the new `lineSegment`
                            lineSegments[numberOfSegments-1] = new LineSegment(temp[0], temp[3]);
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
        return lineSegments;
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
        Point[] points = {p1, p2, p3, p4, p5, p6, p7, p8};

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);


        for (int i = 0; i < bcp.segments().length; i++) {
            LineSegment segment = bcp.segments()[i];

            System.out.println("Segments: " + segment.toString());
        }

        System.out.println("Number of segments: " + bcp.getNumberOfSegments());
        System.out.println("Number of segments: " + bcp.segments().length);
    }
}
