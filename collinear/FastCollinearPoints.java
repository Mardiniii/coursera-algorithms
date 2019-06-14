/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/14/2019
 *  Description: Examines 4 points at a time and checks whether they all lie on
 *  the same line segment, returning all such line segments.
 **************************************************************************** */

import java.util.Arrays;

public class FastCollinearPoints {
    private final Point[] points;
    private int numberOfSegments = 0;
    private LineSegment[] lineSegments = new LineSegment[1];

    public FastCollinearPoints(Point[] pointsArray) {
        validatePoints(pointsArray);

        points = pointsArray;

        Arrays.sort(pointsArray);
    }

    // Return the number of segments detected by the constructor.
    public int getNumberOfSegments() {
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

    public static void main(String[] args) {

    }
}
