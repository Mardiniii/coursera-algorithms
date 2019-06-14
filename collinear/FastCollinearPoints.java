/* *****************************************************************************
 *  Name: Sebastian Zapata Mardini
 *  Date: 06/14/2019
 *  Description: Examines 4 points at a time and checks whether they all lie on
 *  the same line segment, returning all such line segments.
 **************************************************************************** */

public class FastCollinearPoints {


    public FastCollinearPoints(Point[] pointsArray) {
        validatePoints(pointsArray);
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
